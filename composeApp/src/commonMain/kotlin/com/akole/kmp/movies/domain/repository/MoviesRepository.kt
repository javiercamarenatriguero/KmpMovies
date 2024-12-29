package com.akole.kmp.movies.domain.repository

import com.akole.kmp.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun fetchPopularMovies(): Flow<List<Movie>>
    suspend fun fetchMovieById(id: Int): Flow<Movie?>
    suspend fun toggleFavorite(movie: Movie)
}