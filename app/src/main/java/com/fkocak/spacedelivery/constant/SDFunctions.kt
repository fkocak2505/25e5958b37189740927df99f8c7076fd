package com.fkocak.spacedelivery.constant

import com.fkocak.spacedelivery.utils.stateVals.sShipCapacity
import com.fkocak.spacedelivery.utils.stateVals.sShipDurability
import com.fkocak.spacedelivery.utils.stateVals.sShipSpeed
import com.fkocak.spacedelivery.utils.stateVals.sTotalScore


fun calculateTotalScore() = sTotalScore - sumOfAllObligatoryFieldsValue()

fun sumOfAllObligatoryFieldsValue() = sShipDurability + sShipSpeed + sShipCapacity