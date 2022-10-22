package com.example.moviescatalog.MainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviescatalog.*

/* TODO
1. анимация увеличения первого слева постера в Избранном
2. затемнение баннера
3. оформить информацию о фильмах в Галерее
4. сделать общий скролл для Избранного и Галереи
 */

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    Column(modifier = Modifier) {
        Banner()
        FavouriteFilms(title = R.string.favourite, modifier)
        GalleryFilms(title = R.string.gallery, modifier)
    }

}