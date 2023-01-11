package com.example.moviescatalog.screens.MainScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviescatalog.R

/* TODO
1. анимация увеличения первого слева постера в Избранном
 */

@Composable
fun MainScreen(
    navController: NavController,
    onWatchClick: () -> Unit
) {

    Banner(onWatchClick, "https://cultofcinema.com/wp-content/uploads/2017/03/Сплит-360x320.jpg")

    LazyColumn(modifier = Modifier.padding(top = 320.dp)) {
        item() { FavouriteFilms(title = R.string.favourite) }

        item {
            GalleryFilms(
                title = R.string.gallery
            )
        }

        items(galleryFilmData) { item ->
            GalleryFilmsElement(navController, item)
        }
    }

}


private val galleryFilmData = mutableListOf(
    R.drawable.f_1,
    R.drawable.f_2,
    R.drawable.f_3,
    R.drawable.f_4,
    R.drawable.f_5,
)