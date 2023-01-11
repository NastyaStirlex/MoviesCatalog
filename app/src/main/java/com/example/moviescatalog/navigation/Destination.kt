package com.example.moviescatalog.navigation

import com.example.moviescatalog.utils.Constants

sealed class Screen(val route: String) {
    object Signin: Screen(route = Constants.Screen.SIGNIN_SCREEN)
    object Signup: Screen(route = Constants.Screen.SIGNUP_SCREEN)
    object Main: Screen(route = Constants.Screen.MAIN_SCREEN)
    object Movie: Screen(route = Constants.Screen.MOVIE_SCREEN)
    object Profile: Screen(route = Constants.Screen.PROFILE_SCREEN)
    object Review: Screen(route = Constants.Screen.REVIEW_DIALOG)

}

