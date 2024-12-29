package com.akole.kmp.movies.domain.datasource

const val DEFAULT_REGION = "US"
interface RegionDataSource {
    suspend fun fetchRegion(): String
}