package com.akole.kmp.movies.domain.model

data class Movie (
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val backdrop: String?,
    val poster: String,
    val originalTitle: String,
    val originalLanguage: String,
    val popularity: Double,
    val voteAverage: Double,
)
