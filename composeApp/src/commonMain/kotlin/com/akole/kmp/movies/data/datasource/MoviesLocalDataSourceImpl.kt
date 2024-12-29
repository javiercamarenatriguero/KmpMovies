package com.akole.kmp.movies.data.datasource

import com.akole.kmp.movies.data.dto.database.toDatabaseDto
import com.akole.kmp.movies.data.dto.database.toDomainModel
import com.akole.kmp.movies.data.service.database.MoviesDao
import com.akole.kmp.movies.domain.datasource.MoviesLocalDataSource
import com.akole.kmp.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesLocalDataSourceImpl(
    private val moviesDao: MoviesDao,
) : MoviesLocalDataSource {

    override fun fetchPopularMovies(): Flow<List<Movie>> {
        return moviesDao.fetchPopularMovies().map { it.map { movieDto -> movieDto.toDomainModel() } }
    }

    override fun fetchMovieById(id: Int): Flow<Movie?> {
        return moviesDao.fetchMovieById(id).map { it?.toDomainModel() }
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        moviesDao.save(movies.map { it.toDatabaseDto() })
    }
}
