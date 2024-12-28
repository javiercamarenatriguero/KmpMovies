package com.akole.kmp.movies.data.repository

import com.akole.kmp.movies.data.dto.database.toDatabaseDto
import com.akole.kmp.movies.data.dto.database.toDomainModel
import com.akole.kmp.movies.data.dto.network.toDomainModel
import com.akole.kmp.movies.data.service.database.MoviesDao
import com.akole.kmp.movies.data.service.network.MoviesService
import com.akole.kmp.movies.domain.model.Movie
import com.akole.kmp.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MoviesRepositoryImpl(
    private val moviesService: MoviesService,
    private val moviesDao: MoviesDao,
): MoviesRepository {

    override suspend fun fetchPopularMovies(): Flow<List<Movie>> = moviesDao.fetchPopularMovies().onEach { movies ->
        if (movies.isEmpty()) {
            val domainMovies = moviesService.fetchPopularMovies().results.map { it.toDomainModel() }
            moviesDao.save(
                domainMovies.map { it.toDatabaseDto() }
            )
        }
    }.map { it.map { movieDto -> movieDto.toDomainModel() } }

    override suspend fun fetchMovieById(id: Int): Flow<Movie?> =
        moviesDao.fetchMovieById(id).onEach { movie ->
            if (movie == null) {
                val domainMovie = moviesService.fetchMovieById(id).toDomainModel()
                moviesDao.save(
                    listOf(domainMovie.toDatabaseDto())
                )
            }
        }.map { it?.toDomainModel() }
}