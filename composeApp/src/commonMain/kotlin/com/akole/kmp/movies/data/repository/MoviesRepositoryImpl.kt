package com.akole.kmp.movies.data.repository

import com.akole.kmp.movies.data.dto.toDomainModel
import com.akole.kmp.movies.data.service.MoviesService
import com.akole.kmp.movies.domain.model.Movie
import com.akole.kmp.movies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesService: MoviesService,
): MoviesRepository {
    override suspend fun fetchPopularMovies() =
        moviesService.fetchPopularMovies().results.map { it.toDomainModel() }

    override suspend fun fetchMovieById(id: Int): Movie {
        moviesService.fetchMovieById(id).let {
            return it.toDomainModel()
        }
    }
}