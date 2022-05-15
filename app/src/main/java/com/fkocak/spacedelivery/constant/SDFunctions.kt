package com.fkocak.spacedelivery.constant

import androidx.compose.runtime.mutableStateOf
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.data.model.Stations
import com.fkocak.spacedelivery.utils.stateVals.*
import kotlin.math.abs


fun calculateTotalScore() = sTotalScore - sumOfAllObligatoryFieldsValue()

fun sumOfAllObligatoryFieldsValue() = sShipDurability + sShipSpeed + sShipCapacity

fun calculateFormul() {
    sUGS = sShipCapacity * 10000
    sEUS = sShipSpeed * 20
    sDS = sShipDurability * 10000

}

fun calculateRemainingDurabilitySecond() {
    sRemaningDurabilitySeconds = sDS / 1000
}

fun calculateDistanceBetweenEachOther(items: Stations): Int {

    val currentCoordinateX = sCurrentStationInfo?.coordinateX?.toInt()
    val currentCoordinateY = sCurrentStationInfo?.coordinateY?.toInt()

    val itemCoordinateX = items.coordinateX?.toInt()
    val itemCoordinateY = items.coordinateY?.toInt()

    val finalCoordinate =
        Pair(itemCoordinateX!! - currentCoordinateX!!, itemCoordinateY!! - currentCoordinateY!!)

    items.distance?.value = abs(finalCoordinate.first) + abs(finalCoordinate.second)

    return items.distance?.value!!
}

fun returnWorld(){
    val currentStationObj = sAllStationData[0]
    sCurrentStationInfo = Stations(
        currentStationObj.coordinateY,
        currentStationObj.coordinateX,
        mutableStateOf(currentStationObj.need!!),
        currentStationObj.name,
        mutableStateOf(currentStationObj.stock!!),
        currentStationObj.capacity,
        mutableStateOf(currentStationObj.need != 0),
        mutableStateOf(false),
        mutableStateOf(abs(currentStationObj.coordinateX?.toInt()!!) + abs(currentStationObj.coordinateY?.toInt()!!))
    )
}

fun setCurrentStation() {
    returnWorld()

    sAllStationData.forEachIndexed { index, response4Stations ->

        var isFavorite = false

        if (index != 0) {

            sFavoriteStationList.forEachIndexed { _, stations4RoomDB ->
                if (stations4RoomDB.name == response4Stations.name) {
                    isFavorite = true
                }

            }
            sTravellableStationList.add(
                Stations(
                    response4Stations.coordinateY,
                    response4Stations.coordinateX,
                    mutableStateOf(response4Stations.need!!),
                    response4Stations.name,
                    mutableStateOf(response4Stations.stock!!),
                    response4Stations.capacity,
                    mutableStateOf(response4Stations.need != 0),
                    mutableStateOf(isFavorite),
                    mutableStateOf(
                        abs(response4Stations.coordinateX?.toInt()!!) + abs(
                            response4Stations.coordinateY?.toInt()!!
                        )
                    )
                )
            )


        }

    }
}