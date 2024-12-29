package com.akole.kmp.movies.data.repository

import com.akole.kmp.movies.domain.datasource.MoviesLocalDataSource
import com.akole.kmp.movies.domain.datasource.MoviesRemoteDataSource
import com.akole.kmp.movies.domain.datasource.RegionDataSource
import com.akole.kmp.movies.domain.model.Movie
import com.akole.kmp.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class MoviesRepositoryImpl(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource,
    private val regionDataSource: RegionDataSource,
) : MoviesRepository {

    override suspend fun fetchPopularMovies(): Flow<List<Movie>> =
        localDataSource.fetchPopularMovies().onEach { movies ->
            if (movies.isEmpty()) {
                val region = regionDataSource.fetchRegion()
                val remoteMovies = remoteDataSource.fetchPopularMovies(region)
                localDataSource.saveMovies(remoteMovies)
            }
        }

    override suspend fun fetchMovieById(id: Int): Flow<Movie?> =
        localDataSource.fetchMovieById(id).onEach { movie ->
            if (movie == null) {
                val remoteMovie = remoteDataSource.fetchMovieById(id)
                localDataSource.saveMovies(listOf(remoteMovie))
            }
        }

    override suspend fun toggleFavorite(movie: Movie) {
        localDataSource.saveMovies(
            listOf(
                movie.copy(isFavorite = !movie.isFavorite)
            )
        )
    }
}
