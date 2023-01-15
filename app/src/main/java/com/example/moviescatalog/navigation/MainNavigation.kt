package com.example.moviescatalog.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviescatalog.screens.MainScreen.MainScreen
import com.example.moviescatalog.screens.MainScreen.models.MainViewModel
import com.example.moviescatalog.screens.ProfileScreen.ProfileScreen
import com.example.moviescatalog.screens.ProfileScreen.models.ProfileViewModel

fun NavGraphBuilder.mainNavigation(
    navController: NavController
) {
    navigation(
        route = "Main",
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            NavBar(navController = navController) {
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    onReturn = {
                        navController.navigate(Screen.Main.route) {
                            popUpTo("Main")
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onMovieClick = { movieId, isFavourite ->
                        navController.navigate(Screen.Movie.route + "/" + movieId + "/" + isFavourite)
                    },
                    mainViewModel = mainViewModel
                )
            }
        }

        composable(Screen.Profile.route) {
            NavBar(navController = navController) {
                val profileViewModel = hiltViewModel<ProfileViewModel>()
                ProfileScreen(
                    onReturn = {
                        navController.navigate(Screen.Main.route) {
                            popUpTo("Main")
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onLogoutClick = { navController.navigate("Sign") },
                    profileViewModel = profileViewModel
                )
            }
        }
    }
}