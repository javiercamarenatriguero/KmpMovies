package com.akole.kmp.movies.domain.repository

import com.akole.kmp.movies.domain.model.Movie

interface MoviesRepository {
    suspend fun fetchPopularMovies(): List<Movie>
}