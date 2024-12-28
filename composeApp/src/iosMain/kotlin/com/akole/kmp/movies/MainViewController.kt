package com.akole.kmp.movies

import androidx.compose.ui.window.ComposeUIViewController
import com.akole.kmp.movies.data.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {
    val db = getDatabaseBuilder().build()
    MoviesApp(db.moviesDao())
}