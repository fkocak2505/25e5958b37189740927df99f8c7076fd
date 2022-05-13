package com.fkocak.spacedelivery.utils.stateVals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Total Score
 */
var sTotalScore: Int by mutableStateOf(15)

/**
 * Ship Name
 */
var sShipName: String by mutableStateOf("")