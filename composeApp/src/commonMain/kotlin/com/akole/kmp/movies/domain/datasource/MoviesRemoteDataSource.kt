package com.akole.kmp.movies.domain.datasource

import com.akole.kmp.movies.domain.model.Movie

interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<Movie>
    suspend fun fetchMovieById(id: Int): Movie
}
