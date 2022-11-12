package com.example.moviescatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviescatalog.data.models.*
import com.example.moviescatalog.data.movies.MainViewModel
import com.example.moviescatalog.navigation.NavBarScreen
import com.example.moviescatalog.navigation.SetupNavHost
import com.example.moviescatalog.screens.MainScreen.MainScreen
import com.example.moviescatalog.screens.ProfileScreen.ProfileScreen
import com.example.moviescatalog.ui.theme.MoviesCatalogTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.JsonNull.content

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var loginViewModel = LoginViewModel()
    private var registerViewModel = RegisterViewModel()



    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MoviesCatalogTheme {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xff150D0B))
                    ) {
                        var favoritesViewModel = hiltViewModel<FavoritesViewModel>()
                        var mainViewModel = hiltViewModel<MainViewModel>()
                        SetupNavHost(loginViewModel, registerViewModel, favoritesViewModel, mainViewModel)
                    }
                }
        }
    }
}