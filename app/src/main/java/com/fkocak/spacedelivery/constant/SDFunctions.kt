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
        mutableStateOf(abs(currentStationObj.coordinateX?.toInt()!!) + abs(currentStationObj.coordinateY?.toInt()!!))
    )
}