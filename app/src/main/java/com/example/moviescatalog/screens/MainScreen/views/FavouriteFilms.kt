package com.example.moviescatalog.screens.MainScreen

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviescatalog.data.dto.MoviePageDto
import com.example.moviescatalog.ui.theme.Accent
import com.example.moviescatalog.ui.theme.H1

@Composable
fun FavouriteFilms(
    @StringRes title: Int,
    favourites: List<MoviePageDto>,
    onMovieClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(top = 36.dp)) {
        Text(
            text = stringResource(title),
            style = H1,
            color = Accent,
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .padding(bottom = 25.dp)
        )
        FavouriteFilmsRow(favourites = favourites, onMovieClick = onMovieClick, onDeleteClick = onDeleteClick)
    }
}

@Composable
fun FavouriteFilmsElement(
    favouriteMovie: MoviePageDto,
    onMovieClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp, 144.dp)
    ) {
        AsyncImage(
            model = favouriteMovie.poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp))
                .clickable(onClick = { onMovieClick.invoke(favouriteMovie.id) })
        )

        Row {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clickable(onClick = { onDeleteClick.invoke(favouriteMovie.id) })
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
fun FavouriteFilmsRow(
    favourites: List<MoviePageDto>,
    modifier: Modifier = Modifier,
    onMovieClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        contentPadding = PaddingValues(horizontal = 18.dp),
        modifier = modifier

    ) {
        items(favourites) { favouriteMovie ->
            FavouriteFilmsElement(favouriteMovie = favouriteMovie, onMovieClick = onMovieClick, onDeleteClick = onDeleteClick)
        }
    }
}

