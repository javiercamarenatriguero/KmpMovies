package com.akole.kmp.movies.data.repository

import com.akole.kmp.movies.data.fake.dto.toDomainModel
import com.akole.kmp.movies.data.service.MoviesService
import com.akole.kmp.movies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesService: MoviesService,
): MoviesRepository {
    override suspend fun fetchPopularMovies() =
        moviesService.fetchPopularMovies().results.map { it.toDomainModel() }
}