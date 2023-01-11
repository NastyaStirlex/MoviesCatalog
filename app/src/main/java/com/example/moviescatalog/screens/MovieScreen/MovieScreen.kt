package com.example.moviescatalog.screens.MovieScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import com.example.moviescatalog.R
import com.example.moviescatalog.data.models.Review
import com.example.moviescatalog.screens.MovieScreen.models.MovieState
import com.example.moviescatalog.screens.MovieScreen.models.MovieViewModel
import com.example.moviescatalog.ui.theme.*
import java.lang.Float.min


@Composable
fun MovieScreen(
    onBackClick: () -> Unit,
    onAddReviewClick: () -> Unit,
    movieViewModel: MovieViewModel
) {
    val isMovieLoading by movieViewModel.isMovieLoading.observeAsState()
    val movieState by movieViewModel.movieState.observeAsState()


    movieViewModel.getMovieisDetails()

    if(movieState is MovieState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        val name by movieViewModel.nameData.observeAsState()
        val movieImageUrl by movieViewModel.movieImageUrlData.observeAsState()
        val description by movieViewModel.descriptionData.observeAsState()
        val year by movieViewModel.yearData.observeAsState()
        val country by movieViewModel.countryData.observeAsState()
        val duration by movieViewModel.durationData.observeAsState()
        val tagline by movieViewModel.taglineData.observeAsState()
        val producer by movieViewModel.producerData.observeAsState()
        val budget by movieViewModel.budgetData.observeAsState()
        val fees by movieViewModel.feesData.observeAsState()
        val age by movieViewModel.ageData.observeAsState()
        val genres by movieViewModel.genresData.observeAsState()
        val reviews by movieViewModel.reviewsData.observeAsState()

        MovieContent(
            name = name,
            movieImageUrl = movieImageUrl,
            description = description,
            year = year,
            country = country,
            duration = duration,
            tagline = tagline,
            producer = producer,
            budget = budget,
            fees = fees,
            age = age,
            genres = genres,
            reviews = reviews,
            onBackClick,
            onAddReviewClick
        )
    }

}

/**
 * Функция загругления фигуры снизу
 */
fun CornerBasedShape.roundedAtBottom(): CornerBasedShape {
    return copy(
        topStart = CornerSize(0.dp),
        topEnd = CornerSize(0.dp),
        bottomStart = bottomStart,
        bottomEnd = bottomEnd
    )
}

@Composable
fun Lifecycle.observeAsState(): State<Lifecycle.Event> {
    val state = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            state.value = event
        }
        this@observeAsState.addObserver(observer)
        onDispose {
            this@observeAsState.removeObserver(observer)
        }
    }
    return state
}

@Composable
fun MovieContent(
    name: String?,
    movieImageUrl: String?,
    description: String?,
    year: Int?,
    country: String?,
    duration: Int?,
    tagline: String?,
    producer: String?,
    budget: Int?,
    fees: Int?,
    age: Int?,
    genres: List<String>?,
    reviews: List<Review>?,
    onBackClick: () -> Unit,
    onAddReviewClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box() {
                // исчезающий постер
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .clip(RoundedCornerShape(16.dp).roundedAtBottom())
                        .graphicsLayer {
                            alpha = 1f - (scrollState.value.toFloat() / scrollState.maxValue)
                            translationY = 0.5f * scrollState.value
                        }
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.poster),
//                        contentDescription = null,
//                        modifier = Modifier.fillMaxWidth(),
//                        contentScale = ContentScale.Crop
//                    )
                    // poster
                    AsyncImage(
                        model = movieImageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    // gradient
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    0f to SealBrown,
                                    .25f to Color.Transparent,
                                    startY = Float.POSITIVE_INFINITY,
                                    endY = 0f,
                                )
                            )
                    )
                    // Название фильма
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Spacer(modifier = Modifier.weight(1f))
                        if (name != null) {
                            Text(
                                text = name,
                                style = Title,
                                color = BrightWhite,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }
                    }
                }
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.padding(top = 30.dp)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }

            if (description != null) {
                Description(description)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                About(
                    year = year,
                    country = country,
                    duration = duration,
                    tagline = tagline,
                    producer = producer,
                    budget = budget,
                    fees = fees,
                    age = age
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Genres(genres)

            Spacer(modifier = Modifier.height(16.dp))

            // TODO!!!!!!!
            Reviews(onAddReviewClick)

            Review()
        }

        // появляющийся заголовок
        Box(
            modifier = Modifier
                .defaultMinSize(minHeight = 80.dp)
                .graphicsLayer {
                    alpha = min(1f, (scrollState.value.toFloat() / scrollState.maxValue))
                }
                .background(color = SealBrown)
        ) {
            if (name != null) {
                Text(
                    text = name,
                    style = H1,
                    modifier = Modifier
                        .padding(horizontal = 53.dp)
                        .padding(top = 30.dp, bottom = 10.dp),
                    color = BrightWhite
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(top = 30.dp, start = 350.dp)
            ) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = Accent
                )
            }
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    }
}