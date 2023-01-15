package com.example.moviescatalog.navigation

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviescatalog.data.repository.JwtRepository
import com.example.moviescatalog.screens.SignInScreen.SignInScreen
import com.example.moviescatalog.screens.SignInScreen.models.SignInViewModel
import com.example.moviescatalog.screens.SignUpScreen.SignUpScreen
import com.example.moviescatalog.screens.SignUpScreen.models.SignUpViewModel

fun NavGraphBuilder.signNavigation(
    navController: NavController,
    context: Context,
    jwtRepository: JwtRepository
) {
    navigation(
        route = "Sign",
        startDestination = Screen.Signin.route
    ) {
        composable(Screen.Signin.route) {

            val signInViewModel = hiltViewModel<SignInViewModel>()

            SignInScreen(
                onClickLogin = {
                    navController.navigate("Main")
                },
                onRegisterClick = {
                    navController.navigate(Screen.Signup.route) {
                        popUpTo("Sign") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                signInViewModel = signInViewModel,
                context = context,
                jwtRepository = jwtRepository
            )
        }

        composable(Screen.Signup.route) {

            val signUpViewModel = hiltViewModel<SignUpViewModel>()

            SignUpScreen(
                onSuccessfullRegisterClick = { navController.navigate("Main") },
                onAccountClick = {
                    navController.navigate(Screen.Signin.route) {
                        popUpTo("Sign") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                signUpViewModel = signUpViewModel,
                context = context,
                jwtRepository = jwtRepository
            )
        }
    }
}