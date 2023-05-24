package com.example.moviescatalog.screens.MainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviescatalog.R
import com.example.moviescatalog.screens.MainScreen.models.MainState
import com.example.moviescatalog.screens.MainScreen.models.MainViewModel
import com.example.moviescatalog.screens.MainScreen.views.GalleryFilms
import com.example.moviescatalog.screens.MainScreen.views.GalleryFilmsElement
import com.example.moviescatalog.utils.firstOrNull
import com.example.moviescatalog.utils.isFirstLoading
import com.example.moviescatalog.utils.items

/* TODO
1. анимация увеличения первого слева постера в Избранном
 */

@Composable
fun MainScreen(
    onReturn: () -> Unit,
    onMovieClick: (String, Boolean) -> Unit,
    mainViewModel: MainViewModel
) {
    val mainState by mainViewModel.mainStateDate.observeAsState()
    var refreshCount by remember { mutableStateOf(1) }

    val gallery = mainViewModel.galleryFlow.collectAsLazyPagingItems()
    val favourites = mainViewModel.favouritesData.observeAsState().value

    if (gallery.isFirstLoading()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 200.dp)
            )
        }
    } else {
        if (mainState is MainState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Banner(
                {
                    gallery.firstOrNull()?.let {
                        onMovieClick.invoke(
                            it.movieId,
                            mainViewModel.isFavoriteMovie(it.movieId)
                        )
                    }
                },
                gallery.firstOrNull()?.imageUrl ?: "",
            )

            LazyColumn(modifier = Modifier.padding(top = 320.dp)) {
                item() {
                    if (favourites != null) {
                        if (favourites.isNotEmpty()) {
                            FavouriteFilms(
                                title = R.string.favourite,
                                favourites = favourites,
                                onMovieClick = { onMovieClick(it, mainViewModel.isFavoriteMovie(it)) },
                                onDeleteClick = { mainViewModel.onDeleteFavourite(it) }
                            )
                        }
                    }
                }

                item {
                    GalleryFilms(
                        title = R.string.gallery
                    )
                }

                items(gallery) { item ->
                    GalleryFilmsElement(
                        {
                            onMovieClick.invoke(
                                item.movieId,
                                mainViewModel.isFavoriteMovie(item.movieId)
                            )
                        },
                        item
                    )
                }
            }
        }
    }

    // API call
    LaunchedEffect(key1 = refreshCount) {
        mainViewModel.getFavourites()
    }

    LaunchedEffect(key1 = mainState, block = {
        when(mainState) {
            is MainState.DeleteFavouriteSuccessful -> onReturn.invoke()
            else -> {}
        }
    })

}