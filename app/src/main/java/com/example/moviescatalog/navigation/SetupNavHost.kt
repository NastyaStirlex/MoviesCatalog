package com.example.moviescatalog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviescatalog.LaunchScreen.LaunchScreen
import com.example.moviescatalog.SignInScreen.SignInScreen
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
            LaunchScreen(navController = navController)
        }
        composable(route = Screens.Signin.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Screens.Signup.route) {
            //SignupScreen(navController = navController)
        }
        composable(route = Screens.Main.route) {
            //MainScreen(navController: NavController)
        }
        composable(route = Screens.Movie.route) {
            //MovieScreen(navController: NavController)
        }
        composable(route = Screens.Profile.route) {
            //ProfileScreen(navController: NavController)
        }
    }
}