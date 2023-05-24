package com.example.moviescatalog.screens.MovieScreen.models

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviescatalog.data.models.Review
import com.example.moviescatalog.ui.theme.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun OtherReviewElement(
    review: Review
) {
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
                if (!review.isAnonymous) {
                    AsyncImage(
                        model = review.author.avatar,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(com.example.moviescatalog.R.drawable.avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (review.isAnonymous) stringResource(id = com.example.moviescatalog.R.string.anonymous_review)
                        else review.author.nickName,
                        style = Body,
                        color = BrightWhite
                    )
                }
                Box(
                    modifier = Modifier
                        .size(42.dp, 28.dp)
                        .background(
                            color = Color.hsv(
                                review.rating.toFloat() / 10f * 120,
                                .99f,
                                .67f
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = review.rating.toString(),
                        style = Body,
                        color = BrightWhite
                    )
                }
            }
            Text(
                text = review.reviewText,
                style = BodySmall,
                color = BrightWhite
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = LocalDateTime.parse(review.createDateTime)
                        .toLocalDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    style = BodyVerySmall,
                    color = GraySilver
                )
            }
        }
    }
}