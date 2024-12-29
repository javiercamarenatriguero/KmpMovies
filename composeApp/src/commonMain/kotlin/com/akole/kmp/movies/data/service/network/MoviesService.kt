package com.akole.kmp.movies.data.service.network

import com.akole.kmp.movies.data.dto.network.RemoteMovie
import com.akole.kmp.movies.data.dto.network.RemoteResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MoviesService(
    private val client: HttpClient,
) {
    suspend fun fetchPopularMovies(
        region: String,
    ): RemoteResult {
        return client
            .get("/3/discover/movie") {
                parameter("region", region)
                parameter("sort_by", "popularity_desc")
            }
            .body<RemoteResult>()
    }

    suspend fun fetchMovieById(id: Int): RemoteMovie {
        return client
            .get("/3/movie/$id")
            .body<RemoteMovie>()
    }
}
