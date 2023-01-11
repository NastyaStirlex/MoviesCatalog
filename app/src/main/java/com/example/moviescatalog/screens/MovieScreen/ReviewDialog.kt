package com.example.moviescatalog.screens.MovieScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.ui.theme.*

var comment = mutableStateOf("")
var rating = mutableStateOf(0)

@Composable
fun ReviewDialog(
    onCancel: () -> Unit,
    onSave: () -> Unit
) {
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
                        filled = i <= rating.value,
                        onClick = { rating.value = i }
                    )
                }
            }

            ReviewCommentField(
                value = "codcsdc sdhj edbsk cs whjd  dd knsks, dvssejfojo"
            )

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
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, GrayFaded, RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (true) { // if (value)
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
                    onClick = onSave,
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    border = if (true)
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
}