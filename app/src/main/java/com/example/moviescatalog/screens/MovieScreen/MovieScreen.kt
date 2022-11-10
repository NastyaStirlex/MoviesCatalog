package com.example.moviescatalog.screens.MovieScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun MovieScreen() {
//    val state = rememberCollapsingToolbarScaffoldState()
//
//    var enabled by remember { mutableStateOf(true) }
//
//    Box {
//        CollapsingToolbarScaffold(
//            modifier = Modifier.fillMaxSize(),
//            state = state,
//            scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
//            toolbarModifier = Modifier.background(MaterialTheme.colorScheme.primary),
//            enabled = enabled,
//            toolbar = {
//                // Collapsing toolbar collapses its size as small as the that of
//                // a smallest child. To make the toolbar collapse to 50dp, we create
//                // a dummy Spacer composable.
//                // You may replace it with TopAppBar or other preferred composable.
//                Spacer(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                )
//
//                Image(
//                    painter = painterResource(com.example.moviescatalog.R.drawable.poster),
//                    modifier = Modifier
//                        .parallax(0.5f)
//                        .height(300.dp)
//                        .graphicsLayer {
//                            // change alpha of Image as the toolbar expands
//                            alpha = state.toolbarState.progress
//                        },
//                    contentScale = ContentScale.Crop,
//                    contentDescription = null
//                )
//            }
//        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                items(
//                    List(100) { "Hello World!! $it" }
//                ) {
//                    Text(
//                        text = it,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(4.dp)
//                    )
//                }
//            }
//
//            @OptIn(ExperimentalToolbarApi::class)
//            Button(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .align(Alignment.BottomEnd),
//                onClick = {  }
//            ) {
//                Text(text = "Floating Button!")
//            }
//        }
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Checkbox(checked = enabled, onCheckedChange = { enabled = !enabled })
//
//            Text("Enable collapse/expand", fontWeight = FontWeight.Bold)
//        }
//    }
    val state = rememberCollapsingToolbarScaffoldState()
    var enabled by remember { mutableStateOf(true) }

    Box(modifier = Modifier.height(150.dp)) {
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
                        .graphicsLayer {
                            // change alpha of Image as the toolbar expands
                            alpha = state.toolbarState.progress
                        },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(top = 20.dp)) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(
                    text = "Title",
                    fontSize = textSize,
                    modifier = Modifier
                        .road(Alignment.TopStart, Alignment.BottomStart)
                        .padding(40.dp, 35.dp, 16.dp, 16.dp),
                    color = Color.White
                )
                Box(contentAlignment = Alignment.TopEnd) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                    ) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = Color(0xffEF3A01),
                            modifier = Modifier
                        )
                    }
                }

                //}
                //}
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