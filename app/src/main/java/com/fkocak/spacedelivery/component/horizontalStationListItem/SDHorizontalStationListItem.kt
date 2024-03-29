package com.fkocak.spacedelivery.component.horizontalStationListItem

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fkocak.spacedelivery.component.SDButton
import com.fkocak.spacedelivery.component.favoriteButton.SDFavoriteButton
import com.fkocak.spacedelivery.component.text.SDText
import com.fkocak.spacedelivery.constant.DAMAGE_CAPACITY
import com.fkocak.spacedelivery.constant.ScreensNavigation.CREATE_SPACE_SHIP
import com.fkocak.spacedelivery.constant.calculateDistanceBetweenEachOther
import com.fkocak.spacedelivery.constant.returnWorld
import com.fkocak.spacedelivery.constant.setCurrentStation
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.data.model.Stations
import com.fkocak.spacedelivery.data.model.Stations4RoomDB
import com.fkocak.spacedelivery.ui.theme.*
import com.fkocak.spacedelivery.utils.ApiStateView
import com.fkocak.spacedelivery.utils.stateVals.*
import com.fkocak.spacedelivery.vm.StationsVM

@Composable
fun SDHorizontalStationListItem(
    navController: NavHostController,
    items: Stations,
    modifier: Modifier
) {

    val context = LocalContext.current

    val stationsVM: StationsVM = hiltViewModel()

//    stationsVM.getFavoriteStationListFromRoomDB()

    prepareVMListener(context = context, stationsVM = stationsVM, navController = navController)

    Card(
        modifier = modifier,
        backgroundColor = if (items.isHasCapacity?.value!!) LOGO_DARK_BLUE else TEXT_COLOR_GREY,
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {

        ConstraintLayout {

            val (tCapacity, tStock, tNeed, tDistance, tCoordinates, tName) = createRefs()
            val (bTravel, bFavorite) = createRefs()

            SDText(
                text = "Kapasite: ${items.capacity}",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_10),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tCapacity) {
                        top.linkTo(parent.top, MARGIN_10)
                        start.linkTo(parent.start, MARGIN_10)
                    }
            )

            SDText(
                text = "Stok: ${items.stock?.value}",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_10),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tStock) {
                        top.linkTo(tCapacity.bottom)
                        start.linkTo(parent.start, MARGIN_10)
                    }
            )

            SDText(
                text = "İhtiyaç: ${items.need?.value}",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_10),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tNeed) {
                        top.linkTo(tStock.bottom)
                        start.linkTo(parent.start, MARGIN_10)
                    }
            )

            SDText(
                text = "Koordinatlar: ${items.coordinateX}/${items.coordinateY}",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_10),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tCoordinates) {
                        top.linkTo(tNeed.bottom)
                        start.linkTo(parent.start, MARGIN_10)
                    }
            )

            SDText(
                text = "Uzaklık: ${calculateDistanceBetweenEachOther(items)}",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_10),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tDistance) {
                        top.linkTo(tCoordinates.bottom)
                        start.linkTo(parent.start, MARGIN_10)
                    }
            )

            SDText(
                text = "${items.name}",
                style = TypeOfFont.poppinsSemiBoldStyle(TEXT_SIZE_16, TextAlign.Center),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tName) {
                        top.linkTo(tDistance.bottom, MARGIN_15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            SDButton(
                text = "Seyahat Et",
                color = BACKGROUND_HALF_WHITE,
                textColor = LOGO_LIGHT_BLUE,
                style = TypeOfFont.poppinsMediumStyle(TEXT_SIZE_10, TextAlign.Center),
                modifier = Modifier
                    .constrainAs(bTravel) {
                        top.linkTo(tName.bottom, MARGIN_10)
                        start.linkTo(parent.start, MARGIN_15)
                        end.linkTo(parent.end, MARGIN_15)
                        bottom.linkTo(parent.bottom, MARGIN_10)
                    }
            ) {

                if (items.isHasCapacity?.value!!)
                    goTravellingOtherStation(context, navController, items, stationsVM)
                else
                    Toast.makeText(context, "Seyahat Edemezsin..", Toast.LENGTH_SHORT).show()
            }


            SDFavoriteButton(
                items = items,
                modifier = Modifier
                    .padding(6.dp)
                    .size(25.dp)
                    .constrainAs(bFavorite) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }) {
                if (it) {
                    items.isAddedFavorite?.value = true
                    addFavoriteItem2List(items)
                } else {
                    if (sFavoriteStationList.isNotEmpty()) {
                        items.isAddedFavorite?.value = false
                        removeFavoriteItem2List(items)
                    } else
                        Toast.makeText(context, "Favori Listen zaten boş..", Toast.LENGTH_SHORT)
                            .show()
                }

                stationsVM.saveFavoriteStation()

            }
        }
    }
}

private fun addFavoriteItem2List(items: Stations) {
    val findingStation = sFavoriteStationList.find { it.name == items.name }
    findingStation?.let {

    } ?: run {
        sFavoriteStationList.add(
            Stations4RoomDB(
                items.coordinateY,
                items.coordinateX,
                items.need?.value,
                items.name,
                items.stock?.value,
                items.capacity,
                items.isHasCapacity?.value,
                items.isAddedFavorite?.value,
                items.distance?.value
            )
        )

        items.isAddedFavorite?.value = true

    }
}

private fun removeFavoriteItem2List(items: Stations) {
    val findItems = sFavoriteStationList.find { it.name == items.name }
    sFavoriteStationList.remove(findItems)
    items.isAddedFavorite?.value = false
}


private fun goTravellingOtherStation(
    context: Context,
    navController: NavHostController,
    items: Stations,
    stationsVM: StationsVM
) {

    if (sUGS < items.need?.value!!)
        Toast.makeText(
            context,
            "Yeterli Uzay Giysin (UGS) Yok.. Seyehat Edilemez",
            Toast.LENGTH_SHORT
        )
            .show()
    else if (sEUS < items.distance?.value!!) {
        Toast.makeText(context, "Yeterli Zamanın (EUS) Yok.. Seyehat Edilemez", Toast.LENGTH_SHORT)
            .show()
    } else {

        sUGS -= items.need?.value!!
        items.need?.value = 0
        items.stock?.value = items.capacity!!
        items.isHasCapacity?.value = false

        sEUS -= items.distance?.value!!

        sCurrentStationInfo = items
    }

    checkSomeRules(context, navController, stationsVM)


//    if (sRemaningDurabilitySeconds < items.distance?.value!!) {
//        sShipDamageCapacity -= 10
//        val lastRemainingDurabilitySecond = sRemaningDurabilitySeconds
//        calculateRemainingDurabilitySecond()
//        sRemaningDurabilitySeconds -= (items.distance?.value!! - lastRemainingDurabilitySecond)
//    } else {
//        sRemaningDurabilitySeconds -= items.distance?.value!!
//    }

    var aaa = 2

}

private fun checkSomeRules(
    context: Context,
    navController: NavHostController,
    stationsVM: StationsVM
) {

    val hasTravelableStation = sTravellableStationList.find { it.isHasCapacity?.value!! }
    hasTravelableStation?.let {

        if (sUGS == 0) {
            Toast.makeText(
                context,
                "Dağıtılacak malzeme kalmadı. Dünyaya dönülüyor",
                Toast.LENGTH_SHORT
            ).show()

            sTimerStatus = 2
            sShipDamageCapacity = DAMAGE_CAPACITY
            stationsVM.getAllStationsFromApi()
//            navController.popBackStack(route = CREATE_SPACE_SHIP.routes, inclusive = false)

        } else {
            if (sEUS == 0) {
                Toast.makeText(
                    context,
                    "Evrensel Uzay Süresi bitti. Dünyaya dönülüyor",
                    Toast.LENGTH_SHORT
                ).show()

                sTimerStatus = 2
                sShipDamageCapacity = DAMAGE_CAPACITY
                stationsVM.getAllStationsFromApi()

            } else {

                if (sShipDamageCapacity == 0) {
                    Toast.makeText(
                        context,
                        "Uzay aracın hasar kapasitesi doldu. Dünyaya dönülüyor",
                        Toast.LENGTH_SHORT
                    ).show()

                    sTimerStatus = 2
                    sShipDamageCapacity = DAMAGE_CAPACITY
                    stationsVM.getAllStationsFromApi()
                } else {
                }
            }
        }

    } ?: run {
        Toast.makeText(
            context,
            "Seyahat edilecek başka bir istasyon kalmadı. Dünyaya dönülüyor",
            Toast.LENGTH_SHORT
        ).show()
        // TO DO

        sTimerStatus = 2
        sShipDamageCapacity = DAMAGE_CAPACITY
        stationsVM.getAllStationsFromApi()

    }
}

@Composable
private fun prepareVMListener(
    context: Context,
    stationsVM: StationsVM,
    navController: NavHostController
) {
    when (stationsVM.resultOfInsertFavoriteList.value) {
        is ApiStateView.Success -> {
            Toast.makeText(
                context,
                "Favoriye Eklendi",
                Toast.LENGTH_SHORT
            ).show()

        }
        is ApiStateView.Error -> {}
        else -> {}
    }

    when (stationsVM.sAllStationDataResult.value) {
        is ApiStateView.Loading -> {}
        is ApiStateView.Success -> {
            val allStationData =
                ((stationsVM.sAllStationDataResult.value) as ApiStateView.Success).any as MutableList<Response4Stations>

            sAllStationData = allStationData
            sTravellableStationList = mutableListOf()
            setCurrentStation()

            navController.popBackStack(route = CREATE_SPACE_SHIP.routes, inclusive = false)
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
}