package com.example.moviescatalog.screens.MainScreen

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.ui.theme.Accent
import com.example.moviescatalog.ui.theme.H1

@Composable
fun FavouriteFilms(@StringRes title: Int) {
    Column(modifier = Modifier.padding(top = 36.dp)) {
        Text(
            text = stringResource(title),
            style = H1,
            color = Accent,
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .padding(bottom = 25.dp)
        )
        FavouriteFilmsRow()
    }
}

@Composable
fun FavouriteFilmsElement(
    @DrawableRes drawable: Int,
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    Box(
        modifier = Modifier
            .size(100.dp, 144.dp)
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    onClick = {
                        Toast
                            .makeText(ctx, "Image clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
        )

        // Крестик
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    //.clickable(onClick = onDelete)
                    .clickable(onClick = {
                        Toast
                            .makeText(ctx, "Cross clicked", Toast.LENGTH_SHORT)
                            .show()
                    })
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(com.example.moviescatalog.R.drawable.ic_cross),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            top = 4.dp,
                            bottom = 10.dp,
                            end = 4.dp,
                            start = 10.dp
                        )
                )
            }
        }
    }
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