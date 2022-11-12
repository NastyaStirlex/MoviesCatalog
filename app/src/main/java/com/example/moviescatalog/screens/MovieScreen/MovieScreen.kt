package com.example.moviescatalog.screens.MovieScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@Composable
fun MovieScreen(onBackClick: () -> Unit) {
    val state = rememberCollapsingToolbarScaffoldState()
    var enabled by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxHeight(),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                val textSize = (18 + (30 - 18) * state.toolbarState.progress).sp
                Image(
                    painter = painterResource(com.example.moviescatalog.R.drawable.poster),
                    modifier = Modifier
                        .parallax(0.5f)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .graphicsLayer {
                            // change alpha of Image as the toolbar expands
                            alpha = state.toolbarState.progress
                        },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                IconButton(onClick = onBackClick, modifier = Modifier.padding(top = 20.dp)) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(
                    text = "Побег из Шоушенка",
                    fontSize = textSize,
                    modifier = Modifier
                        .road(Alignment.TopStart, Alignment.BottomStart)
                        .padding(40.dp, 30.dp, 16.dp, 16.dp),
                    color = Color.White
                )
                Box(modifier = Modifier.padding(start = 350.dp, top = 20.dp)) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(top = 0.dp)
                    ) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = Color(0xffEF3A01),
                            modifier = Modifier
                        )
                    }
                }
            },
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(100) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier.padding(8.dp)
                    )
                }
                item() {
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.5f)
                    .background(MaterialTheme.colorScheme.secondary)
                    .height(40.dp)
            )
        }
    }
}