package com.akole.kmp.movies.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.kmp.movies.domain.model.Movie
import com.akole.kmp.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {
    var state by mutableStateOf(UiState())
    private set
    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            moviesRepository.fetchPopularMovies().collect { movieList ->
                state = if (movieList.isNotEmpty()) {
                    UiState(
                        loading = false,
                        movies = movieList,
                    )
                } else {
                    UiState(
                        loading = false,
                        movies = emptyList(),
                    )
                }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList(),
    )
}