package com.example.moviescatalog.screens.MainScreen

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.moviescatalog.navigation.Screen
import com.example.moviescatalog.ui.theme.*

@Composable
fun GalleryFilms(@StringRes title: Int) {
    Text(
        text = stringResource(title),
        style = H1,
        color = Accent,
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .padding(bottom = 9.dp, top = 52.dp)
    )
}


@Composable
fun GalleryFilmsElement(navController: NavController, @DrawableRes drawable: Int) {
    Box(
        modifier = Modifier
            .padding(bottom = 18.dp, start = 18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.Movie.route) }
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp, 144.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .defaultMinSize(minHeight = 144.dp)
            ) {
                Text(
                    "Люцифер",
                    color = BrightWhite,
                    style = Body
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "1999 " + "•" + " США",
                    color = BrightWhite,
                    style = BodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "драма, криминал",
                    color = BrightWhite,
                    style = BodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(56.dp, 28.dp)
                        .background(
                            color = Gray,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "4.5",
                        style = Body,
                        color = BrightWhite
                    )
                }
            }
        }
    }
}