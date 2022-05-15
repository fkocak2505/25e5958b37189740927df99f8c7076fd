package com.fkocak.spacedelivery.data.model

import androidx.compose.runtime.MutableState
import androidx.room.Entity

data class Stations(
    val coordinateY: Double? = null,
    val coordinateX: Double? = null,
    var need: MutableState<Int>? = null,
    val name: String? = null,
    val stock: MutableState<Int>? = null,
    val capacity: Int? = null,
    var isHasCapacity: MutableState<Boolean>? = null,
    var isAddedFavorite: MutableState<Boolean>? = null,
    var distance: MutableState<Int>? = null
)