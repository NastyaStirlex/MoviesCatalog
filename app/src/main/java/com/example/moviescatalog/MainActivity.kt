package com.example.moviescatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.moviescatalog.MainScreen.MainScreen
import com.example.moviescatalog.ui.theme.MoviesCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesCatalogTheme {
                Surface(color = Color(0xff150D0B), modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }

        }
    }
}

@Composable
fun MyApp() {
    MainScreen()
}