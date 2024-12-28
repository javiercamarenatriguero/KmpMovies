package com.akole.kmp.movies.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.akole.kmp.movies.data.service.network.MoviesService
import com.akole.kmp.movies.data.repository.MoviesRepositoryImpl
import com.akole.kmp.movies.data.service.database.MoviesDao
import com.akole.kmp.movies.domain.repository.MoviesRepository
import com.akole.kmp.movies.ui.screens.detail.DetailScreen
import com.akole.kmp.movies.ui.screens.detail.DetailViewModel
import com.akole.kmp.movies.ui.screens.home.HomeScreen
import com.akole.kmp.movies.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.api_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation(moviesDao: MoviesDao) {
    val navController = rememberNavController()
    val repository = rememberMoviesRepository(moviesDao)

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(Screen.Detail.route + "/${movie.id}")
                },
                viewModel = HomeViewModel(moviesRepository = repository),
            )
        }
        composable(
            route = Screen.Detail.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = checkNotNull(backStackEntry.arguments?.getInt("movieId"))
            DetailScreen(
                viewModel = DetailViewModel(
                    movieId = movieId,
                    repository = repository,
                ),
            ) {
                navController.popBackStack()
            }
        }
    }
}

private enum class Screen(val route: String) {
    Home("home"),
    Detail("detail"),
}

// Use Remember to create a single instance of the repository
@Composable
private fun rememberMoviesRepository(
    moviesDao: MoviesDao,
    apiKey: String = stringResource(Res.string.api_key),
): MoviesRepository = remember {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        // Set Client configuration
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.themoviedb.org"
                parameters.append("api_key", apiKey)
            }
        }
    }
    MoviesRepositoryImpl(
        moviesService = MoviesService(
            client = client,
        ),
        moviesDao = moviesDao,
    )
}
