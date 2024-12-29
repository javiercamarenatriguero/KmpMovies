package com.akole.kmp.movies

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.akole.kmp.movies.ui.screens.navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun MoviesApp() {
    // Set up the ImageLoader to make the animation smoother
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }

    // Remove warning by providing a KoinContext for an specific platform
    KoinContext {
        Navigation()
    }
}
