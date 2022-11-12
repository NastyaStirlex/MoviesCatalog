package com.example.moviescatalog.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.moviescatalog.data.movies.MainViewModel
import kotlinx.coroutines.flow.Flow
import com.example.moviescatalog.data.movies.Movy
import com.example.moviescatalog.navigation.Screen

@Composable
fun PopularMoviesItems(
    tvShowsResponse: Movy,
    modifier: Modifier = Modifier,
    onItemClick: (Movy) -> Unit
) {
    Spacer(modifier = Modifier.height(44.dp))
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
                .clickable(onClick = {onItemClick(tvShowsResponse)}),
            shape = RoundedCornerShape(8.dp),
            ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                ) {

                }
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                ) {
                    Text(
                        text = tvShowsResponse.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Network:",
                            fontSize = 12.sp,
                            color = Color.Magenta
                        )
                        Text(
                            text = tvShowsResponse.country,
                            fontSize = 12.sp,
                            color = Color.Cyan,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )

                    }

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Date:",
                            fontSize = 12.sp,
                            color = Color.Magenta
                        )
                        Text(
                            text = tvShowsResponse.year.toString(),
                            fontSize = 12.sp,
                            color = Color.Magenta,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )

                    }

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Status:",
                            fontSize = 12.sp,
                            color = Color.Magenta
                        )
                        Text(
                            text = tvShowsResponse.country,
                            color = Color.Magenta,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .offset(16.dp, (-44).dp)
                .fillMaxWidth(0.3f)
                .height(164.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                rememberAsyncImagePainter(model = tvShowsResponse.poster),
                contentDescription = "Popular Tv Show Image",
                contentScale = ContentScale.Crop,
            )

        }
    }


}


@ExperimentalFoundationApi
@Composable
fun MoviesHomeScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF966DE7),
                        Color(0xFF755CD4),
                        Color(0xFF4C48C1)
                    )
                )
            ),
            title = {
                Text(
                    text = "MaxMovie",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            },
        )
    },
        content = { padding->
            MainContent(modifier = Modifier.padding(padding),
                popularMovies = viewModel.popularMovies,
                navController
            )
        })
}

@ExperimentalFoundationApi
@Composable
fun MainContent(
    modifier: Modifier,
    popularMovies:
    Flow<PagingData<Movy>>,
    navController: NavController
) {
    val lazyPopularMoviesItems = popularMovies.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF966DE7),
                        Color(0xFF755CD4),
                        Color(0xFF4C48C1)
                    )
                )
            )
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

//        lazyPopularMoviesItems.apply {
//            when {
//                loadState.refresh is LoadState.Loading -> {
//                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
//                }
//                loadState.append is LoadState.Loading -> {
//                    item { LoadingItem() }
//                }
//                loadState.refresh is LoadState.Error -> {
//                    val e = lazyPopularMoviesItems.loadState.refresh as LoadState.Error
//                    item {
//                        ErrorItem(
//                            message = e.error.localizedMessage!!,
//                            modifier = Modifier.fillParentMaxSize(),
//                            onClickRetry = { retry() }
//                        )
//                    }
//                }
//                loadState.append is LoadState.Error -> {
//                    val e = lazyPopularMoviesItems.loadState.append as LoadState.Error
//                    item {
//                        ErrorItem(
//                            message = e.error.localizedMessage!!,
//                            onClickRetry = { retry() }
//                        )
//                    }
//                }
//            }
//        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        items(
            lazyPopularMoviesItems.itemCount
        ) { index ->
            Box(Modifier.padding(8.dp)) {
                PopularMoviesItems(lazyPopularMoviesItems[index]!!, onItemClick = {
                    navController.navigate("test_screen" + "/${lazyPopularMoviesItems[index]?.id}")

                })
            }
        }
    }

}
