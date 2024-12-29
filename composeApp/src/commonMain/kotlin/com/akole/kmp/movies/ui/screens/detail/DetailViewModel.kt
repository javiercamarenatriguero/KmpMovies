package com.akole.kmp.movies.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.kmp.movies.domain.model.Movie
import com.akole.kmp.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieId: Int,
    private val repository: MoviesRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.fetchMovieById(movieId).collect { movie ->
                if (movie == null) {
                    _state.value = UiState(loading = false)
                    return@collect
                }
                _state.value = UiState(
                    loading = false,
                    movie = movie,
                )
            }
        }
    }

    fun onFavoriteClick() {
        _state.value.movie?.let { movie ->
            viewModelScope.launch {
                repository.toggleFavorite(movie)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
    )
}
