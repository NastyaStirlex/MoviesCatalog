package com.example.moviescatalog.MainScreen

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun GalleryFilms(@StringRes title: Int, modifier: Modifier = Modifier) {
    //Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xffEF3A01),
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .padding(bottom = 9.dp, top = 52.dp)
        )
        //GalleryFilmsColumn()
    //}
}

@Composable
fun GalleryFilmsColumn(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier
            .padding(start = 18.dp)
    ) {
        items(galleryFilmData) { item ->
            GalleryFilmsElement(item)
        }
    }
}

@Composable
fun GalleryFilmsElement(@DrawableRes drawable: Int,
                   modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
            Row(horizontalArrangement = Arrangement.spacedBy(50.dp)) {
                Image(
                    painter = painterResource(drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(132.dp)
                        .height(176.dp)
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

                Card {
                    Text("Name")
                    Text("Year" + "*" + "Country")
                    Text("Drama, criminal")
                }
            }
}

private val galleryFilmData = mutableListOf(
    com.example.moviescatalog.R.drawable.f_1,
    com.example.moviescatalog.R.drawable.f_2,
    com.example.moviescatalog.R.drawable.f_3,
    com.example.moviescatalog.R.drawable.f_4,
    com.example.moviescatalog.R.drawable.f_5,
)