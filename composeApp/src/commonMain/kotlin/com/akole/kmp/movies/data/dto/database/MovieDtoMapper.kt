package com.akole.kmp.movies.data.dto.database

import com.akole.kmp.movies.domain.model.Movie

fun Movie.toDatabaseDto() = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    poster = poster,
    backdrop = backdrop,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity,
    voteAverage = voteAverage,
    isFavorite = isFavorite,
)

fun MovieEntity.toDomainModel() = Movie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    poster = poster,
    backdrop = backdrop,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity,
    voteAverage = voteAverage,
    isFavorite = isFavorite,
)