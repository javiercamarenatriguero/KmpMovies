package com.akole.kmp.movies.data.service

import com.akole.kmp.movies.data.fake.dto.RemoteResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MoviesService(
    private val client: HttpClient,
) {
    suspend fun fetchPopularMovies(): RemoteResult {
        return client
            .get("/discover/movie?sort_by?=popularity.desc")
            .body<RemoteResult>()
    }
}