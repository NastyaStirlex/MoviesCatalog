package com.example.moviescatalog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviescatalog.screens.SignInScreen.SignInScreen
import com.example.moviescatalog.screens.SignUpScreen.SignUpScreen

fun NavGraphBuilder.signNavigation(navController: NavController) {
    navigation(
        route = "Sign",
        startDestination = Screen.Signin.route
    ) {
        composable(Screen.Signin.route) {
            SignInScreen(
                onClickLogin = { navController.navigate("Main") },
                onRegisterClick = { navController.navigate(Screen.Signup.route) }
            )
        }

        composable(Screen.Signup.route) {
            SignUpScreen(
                onGoToMainClick = { navController.navigate("Main") },
                onAccountClick = { navController.navigate(Screen.Signin.route) }
            )
        }
    }
}