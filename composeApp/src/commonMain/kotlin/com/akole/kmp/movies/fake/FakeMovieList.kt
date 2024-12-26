package com.akole.kmp.movies.fake

import com.akole.kmp.movies.model.Movie

val movies = (1..100).map { index ->
    Movie(
        id = index,
        title = "Movie $index",
        poster = "https://picsum.photos/200/300?random=$index",
    )
}