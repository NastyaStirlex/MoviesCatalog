package com.example.moviescatalog.screens.MovieScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.screens.MovieScreen.ReviewDialog.models.ReviewViewModel
import com.example.moviescatalog.screens.MovieScreen.ReviewDialog.models.ReviewState
import com.example.moviescatalog.ui.theme.*

@Composable
fun ReviewDialog(
    movieId: String,
    reviewId: String? = null,
    startComment: String = "",
    startRating: Int = 0,
    startIsAnonymous: Boolean = false,
    onCancel: () -> Unit,
    onSave: () -> Unit,
    reviewViewModel: ReviewViewModel
) {

    val reviewState by reviewViewModel.reviewState.observeAsState()
    val isAnonymous by reviewViewModel.isAnonymous.observeAsState()
    val comment by reviewViewModel.comment.observeAsState()
    val rating by reviewViewModel.rating.observeAsState()
    val correctnessFields by reviewViewModel.correctnessFields.observeAsState()
    val isNewReview = reviewId == null

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = GrayNero,
        contentColor = BrightWhite
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.post_review),
                style = H1
            )
            // ReviewRatingField
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 1..10) {
                    ReviewStar(
                        filled = i <= rating!!,
                        onClick = { reviewViewModel.ratingChange(i) }
                    )
                }
            }

            comment?.let {
                ReviewCommentField(
                    comment = it,
                    commentChange = reviewViewModel::commentChange
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.anonymous_review),
                    style = Body,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = (if (isNewReview) Modifier.clickable { reviewViewModel.anonymousChange(!isAnonymous!!) } else Modifier)
                        .size(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, GrayFaded, RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (isAnonymous == true) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_check_mark),
                            colorFilter = ColorFilter.tint(Gray),
                            contentDescription = null
                        )
                    }
                }
            }

            Column {
                OutlinedButton(
                    onClick = reviewViewModel::onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    border = if (correctnessFields == true)
                        BorderStroke(1.dp, Accent)
                    else BorderStroke(1.dp, GraySilver),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Accent,
                        contentColor = BrightWhite,
                        disabledContainerColor = SealBrown,
                        disabledContentColor = Accent
                    )
                ) {
                    Text(
                        stringResource(R.string.button_save),
                        style = Body
                    )
                }

                TextButton(
                    onClick = onCancel,
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        stringResource(R.string.button_cancel),
                        color = Accent,
                        style = Body
                    )
                }
            }
        }
    }


    LaunchedEffect(key1 = Unit, block = {
        startComment.let {
            startRating.let { it1 ->
                startIsAnonymous.let { it2 ->
                    reviewViewModel.loadReview(
                        movieId = movieId,
                        reviewId = reviewId,
                        comment = it,
                        rating = it1,
                        isAnonymous = it2
                    )
                }
            }
        }
    })

    LaunchedEffect(key1 = reviewState, block = {
        when (reviewState) {
            is ReviewState.AddReviewSuccessfull -> onSave.invoke()
            else -> {}
        }
    })
}