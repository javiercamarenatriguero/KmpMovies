package com.akole.kmp.movies.di

import com.akole.kmp.movies.data.RegionDataSourceIOSImpl
import com.akole.kmp.movies.data.database.getDatabaseBuilder
import com.akole.kmp.movies.domain.datasource.RegionDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder() }
    factoryOf(::RegionDataSourceIOSImpl) bind RegionDataSource::class

}