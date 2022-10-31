package com.example.moviescatalog.MainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.*
import com.example.moviescatalog.R

/* TODO
1. анимация увеличения первого слева постера в Избранном
2. затемнение баннера
3. оформить информацию о фильмах в Галерее
 */

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    LazyColumn{item() {Banner()}}
    LazyColumn(modifier = Modifier.padding(top = 390.dp)) {
        item() {FavouriteFilms(title = R.string.favourite, modifier)}

        item {GalleryFilms(title = R.string.gallery, modifier
            .padding(vertical = 30.dp))}

        items(galleryFilmData) { item ->
            GalleryFilmsElement(item)
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