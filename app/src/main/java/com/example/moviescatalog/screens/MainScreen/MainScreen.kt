package com.example.moviescatalog.screens.MainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviescatalog.R
import com.example.moviescatalog.screens.MainScreen.models.MainState
import com.example.moviescatalog.screens.MainScreen.models.MainViewModel

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
                        FavouriteFilms(
                            title = R.string.favourite,
                            favourites = favourites,
                            onMovieClick = { onMovieClick(it, mainViewModel.isFavoriteMovie(it)) },
                            onDeleteClick = { mainViewModel.onDeleteFavourite(it) }
                        )
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

/**
 * Функция проверки пустоты [ленивой страницы][LazyPagingItems].
 * Возвращает true, если страница пустая.
 */
fun <T : Any> LazyPagingItems<T>.isEmpty(): Boolean {
    return this.itemCount == 0
}

/**
 * Добавляет предметы в [ленивой странице][LazyPagingItems].
 *
 * @param items данные в ленивой странице
 * @param key фабрика стабильных и уникальных ключей, представляющих предмет.
 * Использование одного и того же ключа для нескольких элементов списка не допускается.
 * Тип ключа должен быть сохранен через Bundle на Android.
 * Если передано значение null, позиция в списке будет представлять ключ.
 * Когда вы указываете ключ, позиция прокрутки будет поддерживаться на основе ключа,
 * что означает, что если вы добавите элементы перед текущим видимым элементом,
 * элемент с данным ключом будет сохранен как первый видимый.
 * @param contentType фабрика типов контента для элемента.
 * Композиции элементов одного и того же типа могут быть повторно использованы более эффективно.
 * Обратите внимание, что null является допустимым типом, и элементы такого типа будут считаться совместимыми.
 * @param itemContent содержимое, отображаемое одним элементом
 */
inline fun <T : Any> LazyListScope.items(
    items: LazyPagingItems<T>,
    noinline key: ((item: T?) -> Any)? = null,
    noinline contentType: (item: T?) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.itemCount,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    items[it]?.let { item -> itemContent(item) }
}

fun <T : Any> LazyPagingItems<T>.isFirstLoading(): Boolean {
    return this.loadState.refresh == LoadState.Loading
}

/**
 * Функция, которая возвращает первый элемент [ленивой страницы][LazyPagingItems].
 * Если страница [пустая][isEmpty], то вернётся null.
 */
fun <T : Any> LazyPagingItems<T>.firstOrNull(): T? {
    return if (isEmpty()) null else this[0]
}
