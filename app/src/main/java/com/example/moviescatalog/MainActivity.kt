package com.example.moviescatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.moviescatalog.navigation.SetupNavHost
import com.example.moviescatalog.screens.LaunchScreen.LaunchScreen
import com.example.moviescatalog.screens.SignUpScreen.SignUpScreen
import com.example.moviescatalog.ui.theme.MoviesCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesCatalogTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xff150D0B))
                ) {
                    val navController = rememberNavController()
                    SetupNavHost(navController = navController)
                }
            }
        }
    }
}

