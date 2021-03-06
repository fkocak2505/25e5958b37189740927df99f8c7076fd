package com.fkocak

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fkocak.spacedelivery.constant.ScreensNavigation.*
import com.fkocak.spacedelivery.constant.returnWorld
import com.fkocak.spacedelivery.constant.setCurrentStation
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.data.model.ShipInfo
import com.fkocak.spacedelivery.data.model.Stations
import com.fkocak.spacedelivery.data.model.Stations4RoomDB
import com.fkocak.spacedelivery.ui.theme.Purple200
import com.fkocak.spacedelivery.utils.ApiStateView
import com.fkocak.spacedelivery.utils.stateVals.*
import com.fkocak.spacedelivery.views.navigationScreen.NavigationScreenView
import com.fkocak.spacedelivery.views.spaceShip.CreateSpaceShipScreenView
import com.fkocak.spacedelivery.views.splash.SplashScreenView
import com.fkocak.spacedelivery.vm.StationsVM
import timber.log.Timber
import kotlin.math.abs

@Composable
fun NavigationController() {
    val navController = rememberNavController()

    val stationsVM: StationsVM = hiltViewModel()

    prepareVMListener(stationsVM)

    NavHost(
        navController = navController,
        startDestination = SPLASH.routes
    ) {

        stationsVM.getAllStationsFromApi()
        stationsVM.getSpaceShipInfoFromRoomDB()

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
            CreateSpaceShipScreenView {
                navController.navigate(NAVIGATION_VIEW_SCREEN.routes)
            }
        }

        // Navigation to NavigateScreenView
        composable(NAVIGATION_VIEW_SCREEN.routes) {
            NavigationScreenView(navController)
        }

    }
}

@Composable
private fun prepareVMListener(stationsVM: StationsVM) {

    val context = LocalContext.current

    when (stationsVM.sAllStationDataResult.value) {
        is ApiStateView.Loading -> {
            SpaceDeliveryProgressBar()
        }
        is ApiStateView.Success -> {
            val allStationData =
                ((stationsVM.sAllStationDataResult.value) as ApiStateView.Success).any as MutableList<Response4Stations>

            sAllStationData = allStationData

            stationsVM.getFavoriteStationListFromRoomDB()
        }
        is ApiStateView.Error -> {
            val msg = ((stationsVM.sAllStationDataResult.value) as ApiStateView.Error).error
            Toast.makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    when (stationsVM.shipInfoState.value) {
        is ApiStateView.Success -> {
            val shipInfo =
                ((stationsVM.shipInfoState.value) as ApiStateView.Success).any as ShipInfo
            sShipInfoData = shipInfo
        }
        is ApiStateView.Error -> {
            sShipInfoData = null
        }
        else -> {}
    }

    when (stationsVM.sFavoriteStationDataResultFromDB.value) {
        is ApiStateView.Success -> {
            val shipInfo =
                ((stationsVM.sFavoriteStationDataResultFromDB.value) as ApiStateView.Success).any as MutableList<Stations4RoomDB>
            sFavoriteStationList = shipInfo

            setCurrentStation()
        }
        is ApiStateView.Error -> {

        }
        else -> {}
    }
}


@Composable
fun SpaceDeliveryProgressBar() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = Purple200,
            strokeWidth = Dp(value = 4F)
        )
    }
}