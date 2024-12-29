package com.akole.kmp.movies.data

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import com.akole.kmp.movies.domain.datasource.DEFAULT_REGION
import com.akole.kmp.movies.domain.datasource.RegionDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class RegionDataSourceAndroidImpl(
    private val geocoder: Geocoder,
    private val fusedLocationProviderClient: FusedLocationProviderClient
): RegionDataSource {
    override suspend fun fetchRegion(): String {
        return fusedLocationProviderClient.lastLocation()?.toRegion() ?: DEFAULT_REGION
    }

    private fun Location.toRegion(): String {
        val address = geocoder.getFromLocation(latitude, longitude, 1)
        return address?.firstOrNull()?.countryCode ?: DEFAULT_REGION
    }
}

@SuppressLint("MissingPermission")
private suspend fun FusedLocationProviderClient.lastLocation(): Location? {
    return suspendCancellableCoroutine { continuation ->
        lastLocation.addOnSuccessListener { location ->
            continuation.resume(location)
        }.addOnFailureListener {
            continuation.resume(null)
        }

    }
}