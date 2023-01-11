package com.example.moviescatalog.screens.MovieScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.ui.theme.BodySmall
import com.example.moviescatalog.ui.theme.BrightWhite

@Composable
fun Description(description: String) {
    Text(
        text = description,
        style = BodySmall,
        color = BrightWhite,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}