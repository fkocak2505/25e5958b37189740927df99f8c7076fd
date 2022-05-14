package com.fkocak.spacedelivery.views.navigationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fkocak.spacedelivery.constant.ScreensNavigation.FAVORITE_SCREEN
import com.fkocak.spacedelivery.constant.ScreensNavigation.STATION_SCREEN
import com.fkocak.spacedelivery.ui.theme.LOGO_DARK_BLUE
import com.fkocak.spacedelivery.views.navigationScreen.favorite.FavoriteScreenView
import com.fkocak.spacedelivery.views.navigationScreen.station.StationScreenView

@Composable
fun NavigationScreenView() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            // visible bottom bar only on home and profile screen
            if ((currentRoute(navController = navController) == STATION_SCREEN.routes) or (currentRoute(
                    navController = navController
                ) == FAVORITE_SCREEN.routes)
            ) {
                BottomBar(navController)
            }
        }
    ) {
        BottomBarMain(navController)
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun BottomBarMain(navController: NavHostController) {
    NavHost(navController = navController, startDestination = STATION_SCREEN.routes) {
        // redirect to Home screen
        composable(STATION_SCREEN.routes) {
            StationScreenView()
        }
        // redirect to profile screen
        composable(FAVORITE_SCREEN.routes) {
            FavoriteScreenView()
        }
    }
}


@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        STATION_SCREEN,
        FAVORITE_SCREEN
    )
    BottomNavigation(
        elevation = 5.dp,
        backgroundColor = LOGO_DARK_BLUE
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(
                        text = it.title
                    )
                },
                selected = currentRoute == it.routes,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                onClick = {
                    navController.navigate(it.routes) {
                        launchSingleTop = true
                        navController.graph.startDestinationRoute.let { route ->
                            popUpTo(route!!) {
                                saveState = true
                            }
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}