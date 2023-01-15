package com.example.moviescatalog.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.moviescatalog.data.repository.JwtRepository

// https://medium.com/google-developer-experts/navigating-in-jetpack-compose-78c78d365c6a

@Composable
fun SetupNavHost(navController: NavHostController, context: Context, jwtRepository: JwtRepository) {

    NavHost(
        navController = navController,
        startDestination = "Sign"
    ) {

        signNavigation(navController, context, jwtRepository)

        mainNavigation(navController)

        movieNavigation(navController)

        reviewNavigation(navController)
    }
}
