package com.example.moviescatalog.screens.MainScreen

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavouriteFilms(@StringRes title: Int, modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 36.dp)) {
        Text(
            text = stringResource(title),
            fontFamily = imbplexFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            color = Color(0xffEF3A01),
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .padding(bottom = 25.dp)
        )
        FavouriteFilmsRow()
    }
}

@Composable
fun FavouriteFilmsElement(@DrawableRes drawable: Int,
                     modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    Image(
        painter = painterResource(drawable),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(100.dp)
            .height(144.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Clickable image",
                onClick = {
                    Toast
                        .makeText(ctx, "Image clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            )
    )
}

@Composable
fun FavouriteFilmsRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        contentPadding = PaddingValues(horizontal = 18.dp),
        modifier = modifier

    ) {
        items(favouriteData) { item ->
            FavouriteFilmsElement(item)
        }
    }
}

private val favouriteData = mutableListOf(
    com.example.moviescatalog.R.drawable.f_1,
    com.example.moviescatalog.R.drawable.f_2,
    com.example.moviescatalog.R.drawable.f_3,
    com.example.moviescatalog.R.drawable.f_4,
    com.example.moviescatalog.R.drawable.f_5,
)