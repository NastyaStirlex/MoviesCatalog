package com.example.moviescatalog.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import com.example.moviescatalog.data.models.FavoritesViewModel
import com.example.moviescatalog.data.models.LoginViewModel
import com.example.moviescatalog.data.models.RegisterViewModel
import com.example.moviescatalog.data.movies.MainViewModel
import com.example.moviescatalog.screens.LaunchScreen.LaunchScreen
import com.example.moviescatalog.screens.MainScreen.MainScreen
import com.example.moviescatalog.screens.MovieScreen.MovieScreen
import com.example.moviescatalog.screens.MoviesHomeScreen
import com.example.moviescatalog.screens.ProfileScreen.ProfileScreen
import com.example.moviescatalog.screens.SignInScreen.SignInScreen
import com.example.moviescatalog.screens.SignUpScreen.SignUpScreen
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SetupNavHost(loginViewModel: LoginViewModel, registerViewModel: RegisterViewModel, favoritesViewModel: FavoritesViewModel, mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    val loginProgressBar = loginViewModel.progressBar.value
    val registerProgressBar = registerViewModel.progressBar.value


    NavHost(
        navController = navController,
        startDestination = Screen.Launch.route
    ) {
        composable(route = Screen.Launch.route) {
            LaunchScreen(navController = navController, favoritesViewModel)
        }
        composable(route = Screen.Signin.route) {
            if (loginViewModel.isSuccessLoading.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Signin.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                SignInScreen(
                    onclickLogin = loginViewModel::onLoginPressed,
                    onRegisterClick = { navController.navigate("signup_screen") },
                    loginViewModel,
                    loginProgressBar,
                    favoritesViewModel
                )
            }
        }
        composable("signup_screen") {
            if(registerViewModel.isSuccessLoading.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Signup.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                SignUpScreen(
                    onRegisterClick = registerViewModel::onRegisterPressed,
                    onAccountClick = { navController.navigate(Screen.Signin.route) },
                    registerProgressBar
                )
            }
        }
        composable(route = Screen.Main.route) {
            MainScreen(
                navController = navController,
                onWatchClick = { navController.navigate(Screen.Movie.route) },
                Modifier,
                favoritesViewModel
            )
        }
        composable(route = Screen.Movie.route) {
            MovieScreen(onBackClick = { navController.navigate(Screen.Profile.route) })
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController, onLogoutClick = { navController.navigate(Screen.Signin.route) })
        }

    }
}
