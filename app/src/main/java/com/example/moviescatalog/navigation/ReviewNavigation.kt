package com.example.moviescatalog.navigation

import androidx.navigation.*
import androidx.navigation.compose.dialog
import com.example.moviescatalog.screens.MovieScreen.ReviewDialog

fun NavGraphBuilder.reviewNavigation(navController: NavController) {
    navigation(
        route = "Review",
        startDestination = Screen.Review.route
    ) {
        dialog(
            route = Screen.Review.route,
//            arguments = listOf(
//                navArgument(Destination.Review.Add.MOVIE_ID) {
//                    type = NavType.StringType
//                }
//            )
        ) {
            //val movieId = it.arguments?.getString(Destination.Review.Add.MOVIE_ID)
            ReviewDialog(
                onCancel = { navController.navigateUp() },
                onSave = { navController.navigateUp() },
            )
        }
    }
}