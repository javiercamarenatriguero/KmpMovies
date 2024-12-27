package com.akole.kmp.movies.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.akole.kmp.movies.domain.model.Movie
import com.akole.kmp.movies.theme.dimens.LocalAppDimens
import com.akole.kmp.movies.ui.common.LoadingIndicator
import com.akole.kmp.movies.ui.screens.Screen
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onMovieClick: (Movie) -> Unit,
    viewModel: HomeViewModel,
) {
    Screen {
        // Following Material3 guidelines, change the color when scrolling
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(Res.string.app_name))
                    },
                    scrollBehavior = scrollBehavior,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                )
            }
        ) { padding ->
            val state = viewModel.state
            LoadingIndicator(enabled = state.loading)
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(LocalAppDimens.current.viewSpacing),
                horizontalArrangement = Arrangement.spacedBy(LocalAppDimens.current.viewSpacing),
                verticalArrangement = Arrangement.spacedBy(LocalAppDimens.current.viewSpacing),
                modifier = Modifier.padding(padding)
            ) {
                items(state.movies, key = { it.id }) { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = { onMovieClick(movie) },
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieCard(
    movie: Movie,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.clickable { onClick() },
    ) {
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
