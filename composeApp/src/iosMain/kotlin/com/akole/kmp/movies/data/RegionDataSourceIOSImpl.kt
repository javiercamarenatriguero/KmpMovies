package com.akole.kmp.movies.data

import com.akole.kmp.movies.domain.datasource.DEFAULT_REGION
import com.akole.kmp.movies.domain.datasource.RegionDataSource
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.CLPlacemark
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.coroutines.resume

class RegionDataSourceIOSImpl(
    private val locationManager: CLLocationManager = CLLocationManager(),
    private val geoCoder: CLGeocoder = CLGeocoder(),
): RegionDataSource {
    override suspend fun fetchRegion(): String {
        return getCurrentLocation()?.toRegion() ?: DEFAULT_REGION
    }

    private suspend fun getCurrentLocation(): CLLocation? {
        return suspendCancellableCoroutine { continuation ->
            locationManager.delegate = object: NSObject(), CLLocationManagerDelegateProtocol {
                override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
                    val location = didUpdateLocations.lastOrNull() as? CLLocation
                    locationManager.stopUpdatingLocation()
                    continuation.resume(location)
                }

                override fun locationManager(
                    manager: CLLocationManager,
                    didFailWithError: NSError,
                ) {
                    continuation.resume(null)
                }
            }
            locationManager.requestWhenInUseAuthorization()
            locationManager.startUpdatingLocation()
        }
    }

    private suspend fun CLLocation.toRegion(): String {
        return suspendCancellableCoroutine { continuation ->
            geoCoder.reverseGeocodeLocation(this) { placeMarks, error ->
                if (error != null || placeMarks == null) {
                    continuation.resume(DEFAULT_REGION)
                } else {
                    val countryCode = placeMarks.firstOrNull()?.let { (it as CLPlacemark).ISOcountryCode }
                    continuation.resume(countryCode ?: DEFAULT_REGION)
                }
            }
        }
    }
}
