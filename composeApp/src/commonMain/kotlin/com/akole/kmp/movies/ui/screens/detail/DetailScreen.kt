package com.akole.kmp.movies.ui.screens.detail

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.akole.kmp.movies.theme.dimens.LocalAppDimens
import com.akole.kmp.movies.ui.common.LoadingIndicator
import com.akole.kmp.movies.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    onBack: () -> Unit,
) {
    val state = viewModel.state
    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(state.movie?.title.orEmpty()) },
                    navigationIcon = {
                        IconButton(onClick = { onBack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        )
        { padding ->
            LoadingIndicator(enabled = state.loading)
            state.movie?.let { movie ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = movie.poster,
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(ASPECT_RADIO),
                    )
                    Text(
                        text = movie.title,
                        modifier = Modifier.padding(LocalAppDimens.current.screenSpacing),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            }
        }
    }
}

private const val ASPECT_RADIO = 16f / 9f
