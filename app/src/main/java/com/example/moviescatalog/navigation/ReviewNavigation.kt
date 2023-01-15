package com.example.moviescatalog.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.dialog
import com.example.moviescatalog.screens.MovieScreen.ReviewDialog
import com.example.moviescatalog.screens.MovieScreen.ReviewDialog.models.ReviewViewModel

fun NavGraphBuilder.reviewNavigation(navController: NavController) {
    navigation(
        route = "Review",
        startDestination = Screen.AddReview.route
    ) {
        // ADD
        dialog(
            route = Screen.AddReview.route + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                }
            )
        ) {
            val movieId = it.arguments?.getString("movieId")
            if (movieId != null) {
                val reviewViewModel = hiltViewModel<ReviewViewModel>()
                ReviewDialog(
                    movieId = movieId,
                    onCancel = { navController.navigateUp() },
                    onSave = { navController.navigateUp() },
                    reviewViewModel = reviewViewModel
                )
            }
        }
        // EDIT
        dialog(
            route = Screen.EditReview.route + "/{movieId}" + "/{reviewId}" + "/{comment}" + "/{rating}" + "/{isAnonymous}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                },
                navArgument("reviewId") {
                    type = NavType.StringType
                },
                navArgument("comment") {
                    type = NavType.StringType
                },
                navArgument("rating") {
                    type = NavType.IntType
                },
                navArgument("isAnonymous") {
                    type = NavType.BoolType
                }
            )
        ) {
            val movieId = it.arguments?.getString("movieId")
            val reviewId = it.arguments?.getString("reviewId")
            val comment = it.arguments?.getString("comment")
            val rating = it.arguments?.getInt("rating")
            val isAnonymous = it.arguments?.getBoolean("isAnonymous")

            if (movieId != null && reviewId != null && comment != null && rating != null && isAnonymous != null) {
                val reviewViewModel = hiltViewModel<ReviewViewModel>()
                ReviewDialog(
                    movieId = movieId,
                    reviewId = reviewId,
                    startComment = comment,
                    startRating = rating,
                    startIsAnonymous = isAnonymous,
                    onCancel = { navController.navigateUp() },
                    onSave = { navController.navigateUp() },
                    reviewViewModel = reviewViewModel
                )
            }
        }
    }
}