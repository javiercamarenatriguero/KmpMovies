package com.akole.kmp.movies.data.repository

import com.akole.kmp.movies.domain.datasource.RegionDataSource
import com.akole.kmp.movies.domain.repository.RegionRepository

class RegionRepositoryImpl(
    private val regionDataSource: RegionDataSource
): RegionRepository {
    override suspend fun fetchRegion(): String {
        return regionDataSource.fetchRegion()
    }
}