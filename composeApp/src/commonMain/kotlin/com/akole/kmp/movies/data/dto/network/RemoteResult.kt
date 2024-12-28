package com.akole.kmp.movies.data.dto.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    val page: Int,
    val results: List<Result>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results")val totalResults: Int
)

@Serializable
data class Result(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Double,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("genre_ids")val genreIds: List<Int>,
    @SerialName("original_language")val originalLanguage: String,
    @SerialName("original_title")val originalTitle: String,
    @SerialName("poster_path")val posterPath: String,
    @SerialName("release_date")val releaseDate: String,
    @SerialName("vote_average")val voteAverage: Double,
    @SerialName("vote_count")val voteCount: Int
)