package com.example.moviescatalog.screens.MovieScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.ui.theme.*
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun Genres(genres: List<String>?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.genres),
            style = Body,
            color = BrightWhite,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        FlowRow(
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp,
        ) {
            genres?.forEach { Genre(it) }
        }
    }
}

@Composable
fun Genre(
    name: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Accent, RoundedCornerShape(8.dp))
            .height(27.dp)
    ) {
        Text(
            text = name,
            style = BodyMontserrat,
            color = BaseWhite,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}