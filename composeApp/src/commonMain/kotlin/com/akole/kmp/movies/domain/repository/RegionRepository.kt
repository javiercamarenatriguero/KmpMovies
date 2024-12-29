package com.akole.kmp.movies.domain.repository

interface RegionRepository {
    suspend fun fetchRegion(): String

}