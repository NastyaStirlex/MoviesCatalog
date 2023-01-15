package com.example.moviescatalog.screens.MovieScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviescatalog.R
import com.example.moviescatalog.ui.theme.BodyMontserrat
import com.example.moviescatalog.ui.theme.BodySmall
import com.example.moviescatalog.ui.theme.BrightWhite
import com.example.moviescatalog.ui.theme.SealBrown

@Composable
fun ReviewCommentField(
    comment: String,
    commentChange: (String) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = BrightWhite,
        contentColor = SealBrown,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        TextField(
            value = comment,
            onValueChange = commentChange,
            textStyle = BodySmall,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.review),
                    style = BodyMontserrat,
                    color = Gray,
                    fontSize = 13.7.sp
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = SealBrown,
                focusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
    }
}