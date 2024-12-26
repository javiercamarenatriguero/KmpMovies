package com.akole.kmp.movies.data.fake.dto

import com.akole.kmp.movies.domain.model.Movie

fun Result.toDomainModel() = Movie(
    id = id,
    title = title,
    poster = "https://image.tmdb.org/t/p/w500$posterPath",
    )
