package com.akole.kmp.movies

import androidx.compose.ui.window.ComposeUIViewController
import com.akole.kmp.movies.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    MoviesApp()
}