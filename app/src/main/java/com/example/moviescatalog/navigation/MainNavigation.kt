package com.example.moviescatalog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviescatalog.screens.MainScreen.MainScreen
import com.example.moviescatalog.screens.ProfileScreen.ProfileScreen

fun NavGraphBuilder.mainNavigation(navController: NavController) {
    navigation(
        route = "Main",
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            NavBar(navController = navController) {
                MainScreen(navController) { navController.navigate(Screen.Movie.route) }
            }
        }

        composable(Screen.Profile.route) {
            NavBar(navController = navController) {
                ProfileScreen { navController.navigate(Screen.Signin.route) }
            }
        }
    }
}