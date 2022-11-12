package com.example.moviescatalog.screens.MainScreen

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moviescatalog.R
import com.example.moviescatalog.navigation.Screen

val imbplexFamily = FontFamily(
    Font(R.font.imbplexsansmedium, FontWeight.Medium),
    Font(R.font.imbplexsansbold, FontWeight.Bold),
    Font(R.font.imbplexsanslight, FontWeight.Light),
    Font(R.font.imbplexsansregular, FontWeight.Normal)
)

@Composable
fun GalleryFilms(@StringRes title: Int, modifier: Modifier = Modifier) {

        Text(
            text = stringResource(title),
            fontFamily = imbplexFamily, fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            color = Color(0xffEF3A01),
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .padding(bottom = 9.dp, top = 52.dp)
        )
}


@Composable
fun GalleryFilmsElement(navController: NavHostController, @DrawableRes drawable: Int) {
    val ctx = LocalContext.current
    Box(modifier = Modifier.padding(bottom = 18.dp, start = 18.dp) .clickable(onClick = {navController.navigate(Screen.Movie.route)})) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(132.dp)
                .height(176.dp)
                .clickable(
                    enabled = true,
                    onClickLabel = "Clickable image",
                    onClick = {
                        Toast
                            .makeText(ctx, "Image clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 149.dp)
        ) {
            Text("Люцифер", color = Color(0xffFFFFFF),fontFamily = imbplexFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp,)
            Text("1999 " + "•" + " США", color = Color(0xffFFFFFF),fontFamily = imbplexFamily, fontWeight = FontWeight.Medium,fontSize = 14.sp)
            Text("драма, криминал", color = Color(0xffFFFFFF),fontFamily = imbplexFamily, fontWeight = FontWeight.Medium,fontSize = 14.sp)
        }
    }
}