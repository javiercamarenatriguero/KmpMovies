package com.akole.kmp.movies.domain.datasource

import com.akole.kmp.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    fun fetchPopularMovies(): Flow<List<Movie>>
    fun fetchMovieById(id: Int): Flow<Movie?>
    suspend fun saveMovies(movies: List<Movie>)
}