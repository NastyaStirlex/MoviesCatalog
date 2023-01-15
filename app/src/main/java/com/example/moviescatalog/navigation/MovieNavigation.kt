package com.example.moviescatalog.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.moviescatalog.screens.MovieScreen.MovieScreen
import com.example.moviescatalog.screens.MovieScreen.models.MovieViewModel

fun NavGraphBuilder.movieNavigation(navController: NavController) {
    composable(
        route = Screen.Movie.route + "/{movieId}" + "/{isFavourite}",
        arguments = listOf(
            navArgument("movieId") {
                type = NavType.StringType
            },
            navArgument("isFavourite") {
                type = NavType.BoolType
            }
        )
    ) { it ->
        val movieId = it.arguments?.getString("movieId")
        val isFavorite = it.arguments?.getBoolean("isFavourite")

        val movieViewModel = hiltViewModel<MovieViewModel>()

        if (movieId != null && isFavorite != null) {
            MovieScreen(
                onAddReviewClick = { movieId -> navController.navigate(Screen.AddReview.route + "/" + movieId) },
                onEditReviewClick = { movieId, reviewId, comment, rating, isAnonymous ->
                    navController.navigate(
                        Screen.EditReview.route + "/" + movieId + "/" + reviewId + "/" + comment + "/" + rating + "/" + isAnonymous
                    )
                },
                movieId = movieId,
                isFavourite = isFavorite,
                onBackClick = { navController.navigate("Main") },

                movieViewModel
            )
        }
    }
}