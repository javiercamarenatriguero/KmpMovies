package com.akole.kmp.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.akole.kmp.movies.fake.movies
import com.akole.kmp.movies.model.Movie
import com.akole.kmp.movies.theme.dimens.LocalAppDimens
import com.akole.kmp.movies.theme.dimens.largeDimensions
import com.akole.kmp.movies.theme.dimens.smallDimensions
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MoviesApp() {
    MaterialTheme {
        // Set up the ImageLoader to make the animation smoother
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .crossfade(true)
                .logger(DebugLogger())
                .build()
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(LocalAppDimens.current.viewSpacing),
                horizontalArrangement = Arrangement.spacedBy(LocalAppDimens.current.viewSpacing),
                verticalArrangement = Arrangement.spacedBy(LocalAppDimens.current.viewSpacing),
            ) {
                items(movies, key = {it.id}) { movie ->
                    MovieCard(movie = movie)
                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(ASPECT_RADIO)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.surface),
        ) {
            AsyncImage(
                model = movie.poster,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(ASPECT_RADIO)
                    .clip(MaterialTheme.shapes.small),
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                modifier = Modifier.padding(LocalAppDimens.current.viewSpacing),
            )
        }
    }
}

private const val ASPECT_RADIO = 2 / 3f