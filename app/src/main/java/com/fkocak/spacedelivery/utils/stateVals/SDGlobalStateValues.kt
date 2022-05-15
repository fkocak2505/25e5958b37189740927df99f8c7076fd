package com.fkocak.spacedelivery.utils.stateVals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fkocak.spacedelivery.constant.DAMAGE_CAPACITY
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.data.model.ShipInfo
import com.fkocak.spacedelivery.data.model.Stations
import com.fkocak.spacedelivery.data.model.Stations4RoomDB

/**
 * Total Score
 */
var sTotalScore: Int by mutableStateOf(15)

/**
 * Ship Name
 */
var sShipName: String by mutableStateOf("")

/**
 * Searched Text
 */
var sSearchedText: String by mutableStateOf("")

/**
 * Ship Durability states..
 */
var sShipDurability: Int by mutableStateOf(0)
var sShipDurabilityValueRange: ClosedFloatingPointRange<Float> by mutableStateOf(1f..13f)
var sDurabilityIsEnabled: Boolean by mutableStateOf(true)

/**
 * Ship Speed states..
 */
var sShipSpeed: Int by mutableStateOf(0)
var sShipSpeedValueRange: ClosedFloatingPointRange<Float> by mutableStateOf(1f..13f)
var sSpeedIsEnabled: Boolean by mutableStateOf(false)

/**
 * Ship Capacity states..
 */
var sShipCapacity: Int by mutableStateOf(0)
var sShipCapacityValueRange: ClosedFloatingPointRange<Float> by mutableStateOf(1f..13f)
var sCapacityIsEnabled: Boolean by mutableStateOf(false)

/**
 * Ship Damage Capacity: 100br
 */
var sShipDamageCapacity: Int by mutableStateOf(DAMAGE_CAPACITY)

/**
 * ShipInfoData
 */
var sShipInfoData: ShipInfo? by mutableStateOf(null)

/**
 * Button Text..
 */
var sButonText: String by mutableStateOf("Devam Et")

/**
 * All Station Data State..
 */
var sAllStationData = mutableStateListOf<Response4Stations>().toMutableList()

/**
 * UGS Value
 */
var sUGS: Int by mutableStateOf(0)

/**
 * EUS Value
 */
var sEUS: Int by mutableStateOf(0)

/**
 * DS Value
 */
var sDS: Int by mutableStateOf(0)

/**
 * Remaining Durability Seconds
 */
var sRemaningDurabilitySeconds: Int by mutableStateOf(0)

/**
 * Current Station Info
 */
var sCurrentStationInfo: Stations? by mutableStateOf(null)

/**
 * Travellable Stations List..
 */
var sTravellableStationList = mutableStateListOf<Stations>()

/**
 * Favorites Stations List..
 */
var sFavoriteStationList = mutableStateListOf<Stations4RoomDB>().toMutableList()


