package com.fkocak

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fkocak.spacedelivery.constant.ScreensNavigation.CREATE_SPACE_SHIP
import com.fkocak.spacedelivery.constant.ScreensNavigation.SPLASH
import com.fkocak.spacedelivery.views.spaceShip.CreateSpaceShipScreenView
import com.fkocak.spacedelivery.views.splash.SplashScreenView

@Composable
fun NavigationController() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SPLASH.routes
    ) {

        // Splash Compose
        composable(SPLASH.routes) {
            SplashScreenView {
                navController.navigate(CREATE_SPACE_SHIP.routes) {
                    popUpTo(SPLASH.routes) {
                        inclusive = true
                    }
                }
            }
        }

        // Navigation to Create Space Ship Screen
        composable(CREATE_SPACE_SHIP.routes) {
//                NavigationScreen()
            CreateSpaceShipScreenView()
        }

    }
}