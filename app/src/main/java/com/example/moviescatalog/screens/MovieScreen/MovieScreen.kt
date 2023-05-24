package com.example.moviescatalog.screens.MovieScreen

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviescatalog.R
import com.example.moviescatalog.screens.MovieScreen.models.MovieState
import com.example.moviescatalog.screens.MovieScreen.models.MovieViewModel
import com.example.moviescatalog.screens.MovieScreen.models.OtherReviewElement
import com.example.moviescatalog.ui.theme.*
import java.lang.Float.min
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun MovieScreen(
    onAddReviewClick: (movieId: String) -> Unit,
    onEditReviewClick: (movieId: String, reviewId: String, comment: String, rating: Int, isAnonymous: Boolean) -> Unit,
    movieId: String,
    isFavourite: Boolean,
    onBackClick: () -> Unit,
    movieViewModel: MovieViewModel
) {

    val movieState by movieViewModel.movieState.observeAsState()
    var refreshCount by remember { mutableStateOf(1) }


    if (movieState is MovieState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        val isFavourite by movieViewModel.isFavouriteData.observeAsState()
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
        val userReview by movieViewModel.userReview.observeAsState()
        val otherReviews by movieViewModel.otherReviews.observeAsState()

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
                            .clip(RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomEnd = 16.dp,
                                bottomStart = 16.dp
                            ))
                            .graphicsLayer {
                                alpha =
                                    1f - (scrollState.value.toFloat() / scrollState.maxValue)
                                translationY = 0.5f * scrollState.value
                            }
                    ) {
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
                                    text = name!!,
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


                description?.let { Description(it) }

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

                // REVIEWS TITLE
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.reviews),
                        style = Body,
                        color = BrightWhite,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    if (userReview == null) {
                        Image(
                            painter = painterResource(R.drawable.ic_plus),
                            colorFilter = ColorFilter.tint(Accent),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable(onClick = { onAddReviewClick(movieId) })
                        )
                    }
                }
                if (userReview != null) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 8.dp)
                            .border(1.dp, GrayFaded, RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                if (!userReview!!.isAnonymous) {
                                    AsyncImage(
                                        model = userReview!!.author.avatar,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                    )
                                } else {
                                    Image(
                                        painter = painterResource(R.drawable.avatar),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                    )
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = if (userReview!!.isAnonymous) stringResource(id = R.string.anonymous_review) else userReview!!.author.nickName,
                                        style = Body,
                                        color = BrightWhite
                                    )
                                    Text(
                                        text = stringResource(R.string.my_review),
                                        style = BodyVerySmall,
                                        color = GraySilver
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .size(42.dp, 28.dp)
                                        .background(
                                            color = Color.hsv(
                                                userReview!!.rating.toFloat() / 10f * 120,
                                                .99f,
                                                .67f
                                            ),
                                            shape = RoundedCornerShape(16.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = userReview!!.rating.toString(),
                                        style = Body,
                                        color = BrightWhite
                                    )
                                }
                            }

                            Text(
                                text = userReview!!.reviewText,
                                style = BodySmall,
                                color = BrightWhite
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = LocalDateTime.parse(userReview!!.createDateTime)
                                        .toLocalDate()
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                    style = BodyVerySmall,
                                    color = GraySilver
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Image(
                                    painter = painterResource(R.drawable.ic_edit),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .clickable(onClick = {
                                            onEditReviewClick.invoke(
                                                movieId,
                                                userReview!!.id,
                                                userReview!!.reviewText,
                                                userReview!!.rating,
                                                userReview!!.isAnonymous
                                            )
                                        })
                                )
                                Image(
                                    painter = painterResource(R.drawable.ic_delete),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .clickable(onClick = movieViewModel::onDeleteReviewClick)
                                )

                            }
                        }
                    }
                }
                // REVIEWS
                otherReviews?.forEach {
                    Column {
                        OtherReviewElement(review = it)
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp)
                    .graphicsLayer {
                        alpha =
                            min(1f, (scrollState.value.toFloat() / scrollState.maxValue))
                    }
                    .background(color = SealBrown)
            ) {
                if (name != null) {
                    Text(
                        text = name!!,
                        style = H1,
                        modifier = Modifier
                            .padding(horizontal = 53.dp)
                            .padding(top = 30.dp, bottom = 15.dp),
                        color = BrightWhite
                    )
                }
                // FAVOURITE ICON
                IconToggleButton(
                    checked = isFavourite!!,
                    onCheckedChange = {
                        movieViewModel.onToggleFavourite(
                            movieId = movieId,
                            isFavourite = it
                        )
                    },
                    modifier = Modifier.padding(top = 30.dp, start = 350.dp)
                ) {
                    val transition = updateTransition(isFavourite, label = "Transition")

                    val tint by transition.animateColor(label = "iconColor") {
                        if (it == true) Accent else Accent
                    }
                    val size by transition.animateDp(
                        transitionSpec = {
                            if (false isTransitioningTo true) {
                                keyframes {
                                    durationMillis = 250
                                    25.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                                    30.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                                    35.dp at 75 // ms
                                    30.dp at 150 // ms
                                }
                            } else {
                                spring(stiffness = Spring.StiffnessVeryLow)
                            }
                        },
                        label = "Size"
                    ) { if (it == false) 25.dp else 30.dp }

                    Icon(
                        imageVector = if (isFavourite == true) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        tint = tint,
                        modifier = Modifier.size(size)
                    )
                }

                // BACK ICON
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

    // API call
    LaunchedEffect(key1 = refreshCount) {
        isFavourite.let { movieViewModel.getMoviesDetails(movieId = movieId, isFavorite = it) }
    }
}