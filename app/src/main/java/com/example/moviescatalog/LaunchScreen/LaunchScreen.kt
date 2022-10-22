package com.example.moviescatalog.LaunchScreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.ui.theme.MoviesCatalogTheme
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen() {
    MoviesCatalogTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val scale = remember {
                androidx.compose.animation.core.Animatable(0f)
            }
            // Animation
            LaunchedEffect(key1 = true) {
                scale.animateTo(
                    targetValue = 2.1f,
                    // tween Animation
                    animationSpec = tween(
                        durationMillis = 800,
                        easing = {
                            OvershootInterpolator(2.7f).getInterpolation(it)
                        }))
                // Customize the delay time
                delay(3000L)
                //navController.navigate("main_screen")
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically)) {
                Image(
                    painter = painterResource(R.drawable.icon),
                    contentDescription = "Logo",
                    modifier = Modifier.scale(scale.value)
                )
                Image(
                    painter = painterResource(R.drawable.appname),
                    contentDescription = "Name",
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .scale(scale.value)
                )
            }
        }
    }
}
