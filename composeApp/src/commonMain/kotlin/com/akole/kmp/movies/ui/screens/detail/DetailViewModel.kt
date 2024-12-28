package com.akole.kmp.movies.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.kmp.movies.domain.model.Movie
import com.akole.kmp.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieId: Int,
    private val repository: MoviesRepository,
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            repository.fetchMovieById(movieId).collect { movie ->
                if (movie == null) {
                    state = UiState(loading = false)
                    return@collect
                }
                state = UiState(
                    loading = false,
                    movie = movie,
                )
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
    )
}
