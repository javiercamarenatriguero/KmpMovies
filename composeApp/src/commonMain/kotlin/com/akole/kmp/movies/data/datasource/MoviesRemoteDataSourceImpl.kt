package com.akole.kmp.movies.data.datasource

import com.akole.kmp.movies.data.dto.network.toDomainModel
import com.akole.kmp.movies.data.service.network.MoviesService
import com.akole.kmp.movies.domain.datasource.MoviesRemoteDataSource
import com.akole.kmp.movies.domain.model.Movie

class MoviesRemoteDataSourceImpl(
    private val moviesService: MoviesService,
) : MoviesRemoteDataSource {

    override suspend fun fetchPopularMovies(region: String): List<Movie> {
        return moviesService.fetchPopularMovies(region).results.map { it.toDomainModel() }
    }

    override suspend fun fetchMovieById(id: Int): Movie {
        return moviesService.fetchMovieById(id).toDomainModel()
    }
}
