package com.akole.kmp.movies.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.akole.kmp.movies.data.service.MoviesService
import com.akole.kmp.movies.data.fake.movies
import com.akole.kmp.movies.data.repository.MoviesRepositoryImpl
import com.akole.kmp.movies.ui.screens.detail.DetailScreen
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
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val apiKey = stringResource(Res.string.api_key)
    val client = remember {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json{
                        ignoreUnknownKeys = true
                    }
                )
            }
            // Set Client configuration
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org/3"
                    parameters.append("api_key", apiKey)
                }
            }
        }
    }
    val viewModel = viewModel {
        HomeViewModel(
            MoviesRepositoryImpl(
                MoviesService(
                    client = client,
                )
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(Screen.Detail.route + "/${movie.id}")
                },
                viewModel = viewModel,
            )
        }
        composable(
            route = Screen.Detail.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            DetailScreen(movies.first { it.id == movieId }) {
                navController.popBackStack()
            }
        }
    }
}

private enum class Screen(val route: String) {
    Home("home"),
    Detail("detail"),
}