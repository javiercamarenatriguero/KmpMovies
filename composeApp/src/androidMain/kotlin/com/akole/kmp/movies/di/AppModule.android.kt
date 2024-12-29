package com.akole.kmp.movies.di

import android.location.Geocoder
import com.akole.kmp.movies.data.RegionDataSourceAndroidImpl
import com.akole.kmp.movies.data.database.getDatabaseBuilder
import com.akole.kmp.movies.domain.datasource.RegionDataSource
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder(get()) }
    factory { Geocoder(get()) }
    factory { LocationServices.getFusedLocationProviderClient(androidContext()) }
    // Add binding for RegionDataSource
    factoryOf (::RegionDataSourceAndroidImpl) bind RegionDataSource::class
}
