package com.akole.kmp.movies.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import coil3.compose.AsyncImage
import com.akole.kmp.movies.domain.model.Movie
import com.akole.kmp.movies.theme.dimens.LocalAppDimens
import com.akole.kmp.movies.ui.common.LoadingIndicator
import com.akole.kmp.movies.ui.common.property
import com.akole.kmp.movies.ui.screens.Screen
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.details_original_language
import kmpmovies.composeapp.generated.resources.details_original_title
import kmpmovies.composeapp.generated.resources.details_popularity
import kmpmovies.composeapp.generated.resources.details_release_date
import kmpmovies.composeapp.generated.resources.details_vote_average
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    onBack: () -> Unit,
) {
    val state = viewModel.state
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Screen {
        Scaffold(
            topBar = {
                DetailTopBar(state.movie?.title, onBack, scrollBehavior)
            }
        )
        { padding ->
            LoadingIndicator(enabled = state.loading)
            state.movie?.let { movie ->
                MovieDetail(
                    movie = movie,
                    modifier = Modifier.padding(padding),
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String?,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(title.orEmpty()) },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}


@Composable
private fun MovieDetail(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = movie.backdrop ?: movie.poster,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ASPECT_RADIO),
        )
        Text(
            text = movie.overview,
            modifier = Modifier.padding(LocalAppDimens.current.screenSpacing),
        )
        Text(
            text = buildAnnotatedString {
                property(
                    name = stringResource(Res.string.details_original_language),
                    value = movie.originalLanguage,
                )
                property(
                    name = stringResource(Res.string.details_original_title),
                    value = movie.originalTitle,
                )
                property(
                    name = stringResource(Res.string.details_popularity),
                    value = movie.popularity.toString(),
                )
                property(
                    name = stringResource(Res.string.details_release_date),
                    value = movie.releaseDate,
                )
                property(
                    name = stringResource(Res.string.details_vote_average),
                    value = movie.voteAverage.toString(),
                    end = true,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(LocalAppDimens.current.screenSpacing),
        )
    }
}

private const val ASPECT_RADIO = 16f / 9f
