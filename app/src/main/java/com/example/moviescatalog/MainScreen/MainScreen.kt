package com.example.moviescatalog.MainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.*

/* TODO
1. анимация увеличения первого слева постера в Избранном
2. затемнение баннера
3. оформить информацию о фильмах в Галерее
4. сделать общий скролл для Избранного и Галереи
 */

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    LazyColumn{item() {Banner()}}
    //Spacer(modifier = Modifier.height(200.dp))
    LazyColumn(modifier = Modifier.padding(top = 390.dp)) {
        item() {FavouriteFilms(title = R.string.favourite, modifier)}

        item {GalleryFilms(title = R.string.gallery, modifier)}
        items(galleryFilmData) { item ->
            GalleryFilmsElement(item)
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