package com.example.moviescatalog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviescatalog.utils.Constants

sealed class Screens(val route: String) {
    object Launch: Screens(route = Constants.Screens.LAUNCH_SCREEN)
    object Signin: Screens(route = Constants.Screens.SIGNIN_SCREEN)
    object Signup: Screens(route = Constants.Screens.SIGNUP_SCREEN)
    object Main: Screens(route = Constants.Screens.MAIN_SCREEN)
    object Movie: Screens(route = Constants.Screens.MOVIE_SCREEN)
    object Profile: Screens(route = Constants.Screens.PROFILE_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Launch.route
    ) {
        composable(route = Screens.Launch.route) {
            //LaunchScreen()
        }
        composable(route = Screens.Signin.route) {
            //SigninScreen()
        }
        composable(route = Screens.Signup.route) {
            //SignupScreen()
        }
        composable(route = Screens.Main.route) {
            //MainScreen()
        }
        composable(route = Screens.Movie.route) {
            //MovieScreen()
        }
        composable(route = Screens.Profile.route) {
            //ProfileScreen()
        }
    }
}