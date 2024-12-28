package com.akole.kmp.movies.data.dto.network

import com.akole.kmp.movies.domain.model.Movie

fun Result.toDomainModel() = Movie(
    id = id,
    title = title,
    poster = "https://image.tmdb.org/t/p/w185$posterPath",
    overview = overview,
    releaseDate = releaseDate,
    backdrop = backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" },
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity,
    voteAverage = voteAverage,
)

fun RemoteMovie.toDomainModel() = Movie(
    id = id,
    title = title,
    poster = "https://image.tmdb.org/t/p/w185$posterPath",
    overview = overview,
    releaseDate = releaseDate,
    backdrop = backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" },
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity,
    voteAverage = voteAverage,
)
