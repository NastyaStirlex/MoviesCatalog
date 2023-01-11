package com.example.moviescatalog.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.moviescatalog.screens.MovieScreen.MovieScreen
import com.example.moviescatalog.screens.MovieScreen.models.MovieViewModel

fun NavGraphBuilder.movieNavigation(navController: NavController) {
    composable(
        route = Screen.Movie.route,
//        arguments = listOf(
//            navArgument(Screen.Movie.MOVIE_ID) {
//                type = NavType.StringType
//            },
//            navArgument(Screen.Movie.IS_FAVORITE) {
//                type = NavType.BoolType
//            }
//        )
    ) {
        //val movieId = it.arguments?.getString(Screen.Movie.MOVIE_ID)
        //val isFavorite = it.arguments?.getBoolean(Screen.Movie.IS_FAVORITE)
        val movieViewModel = hiltViewModel<MovieViewModel>()
        MovieScreen(
            onBackClick = { navController.navigate("Main") },
            onAddReviewClick = { navController.navigate("Review") },
            movieViewModel
        )
    }
}