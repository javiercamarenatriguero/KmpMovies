package com.akole.kmp.movies.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.akole.kmp.movies.fake.movies
import com.akole.kmp.movies.ui.screens.detail.DetailScreen
import com.akole.kmp.movies.ui.screens.home.HomeScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(Screen.Detail.route + "/${movie.id}")
                }
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