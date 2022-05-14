package com.fkocak.spacedelivery.constant

import com.fkocak.spacedelivery.utils.stateVals.*


fun calculateTotalScore() = sTotalScore - sumOfAllObligatoryFieldsValue()

fun sumOfAllObligatoryFieldsValue() = sShipDurability + sShipSpeed + sShipCapacity

fun calculateFormul(){
    sUGS = sShipCapacity * 10000
    sEUS = sShipSpeed * 20
    sDS = sShipDurability * 10000
}