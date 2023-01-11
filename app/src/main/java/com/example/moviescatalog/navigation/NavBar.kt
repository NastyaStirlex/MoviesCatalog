package com.example.moviescatalog.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviescatalog.R
import com.example.moviescatalog.ui.theme.Accent
import com.example.moviescatalog.ui.theme.Footnote
import com.example.moviescatalog.ui.theme.GrayBokara
import com.example.moviescatalog.ui.theme.GraySilver


@Composable
fun NavBar(
    navController: NavController,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                contentColor = GrayBokara,
                containerColor = GrayBokara
            ) {
                NavBarItem(
                    navController = navController,
                    route = Screen.Main.route,
                    painter = painterResource(id = R.drawable.ic_main),
                    text = stringResource(id = R.string.bottom_navigation_main),
                )
                NavBarItem(
                    navController = navController,
                    route = Screen.Profile.route,
                    painter = painterResource(id = R.drawable.ic_profile),
                    text = stringResource(id = R.string.bottom_navigation_profile),
                )
            }
        }
    ) {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            content.invoke()
        }
    }
}

@Composable
private fun RowScope.NavBarItem(
    navController: NavController,
    route: String,
    painter: Painter,
    text: String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBarItem(
        icon = {
            Box(
                modifier = Modifier.size(28.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painter,
                    contentDescription = null
                )
            }
        },
        label = {
            Text(
                text = text,
                style = Footnote
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == route } == true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Accent,
            selectedTextColor = Accent,
            indicatorColor = GrayBokara,
            unselectedIconColor = GraySilver,
            unselectedTextColor = GraySilver,
        ),
        onClick = {
            navController.navigate(route) {
                popUpTo(Screen.Main.route) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}