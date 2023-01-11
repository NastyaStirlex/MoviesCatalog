package com.example.moviescatalog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetupNavHost(navController: NavHostController) {
    // TODO!!!!!!!
    // перед composable каждого экрана нужно заготавливать его viewModel и передавать модель как параметр вот так:
    // val dailyViewModel = hiltViewModel<DailyViewModel>()
    // DailyScreen(
    //  navController = navController,
    //  dailyViewModel = dailyViewModel
    // )

    NavHost(
        navController = navController,
        startDestination = "Sign"
    ) {
        signNavigation(navController)
        mainNavigation(navController)
        movieNavigation(navController)
        reviewNavigation(navController)

//        composable(route = Screen.Signin.route) {
//            if (loginViewModel.isSuccessLoading.value) {
//                LaunchedEffect(key1 = Unit) {
//                    navController.navigate(Screen.Main.route) {
//                        popUpTo(Screen.Signin.route) {
//                            inclusive = true
//                        }
//                    }
//                }
//            } else {
//                SignInScreen(
//                    onclickLogin = loginViewModel::onLoginPressed,
//                    onRegisterClick = { navController.navigate("signup_screen") },
//                    loginViewModel,
//                    loginProgressBar,
//                    favoritesViewModel
//                )
//            }
//        }
//        composable("signup_screen") {
//            if (registerViewModel.isSuccessLoading.value) {
//                LaunchedEffect(key1 = Unit) {
//                    navController.navigate(Screen.Main.route) {
//                        popUpTo(Screen.Signup.route) {
//                            inclusive = true
//                        }
//                    }
//                }
//            } else {
//                SignUpScreen(
//                    onRegisterClick = registerViewModel::onRegisterPressed,
//                    onAccountClick = { navController.navigate(Screen.Signin.route) },
//                    registerProgressBar
//                )
//            }
//        }
//        composable(route = Screen.Main.route) {
//            MainScreen(
//                navController = navController,
//                onWatchClick = { navController.navigate(Screen.Movie.route) },
//                Modifier,
//                favoritesViewModel
//            )
//        }
//        dialog(route = Screen.Review.route) {
//            ReviewDialog(
//                onCancel = { navController.navigateUp() },
//                onSave = { navController.navigateUp() }
//            )
//        }
//        composable(route = Screen.Movie.route) {
//            MovieScreen(
//                onBackClick = { navController.navigate(Screen.Profile.route) },
//                onAddReviewClick = { navController.navigate(Screen.Review.route) }
//            )
//        }
//        composable(route = Screen.Profile.route) {
//            ProfileScreen(
//                navController,
//                onLogoutClick = { navController.navigate(Screen.Signin.route) }
//            )
//        }

    }
}
