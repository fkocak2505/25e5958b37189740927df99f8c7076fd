package com.fkocak.spacedelivery.data.model

import androidx.compose.runtime.MutableState
import androidx.room.Entity
import androidx.room.PrimaryKey

//==================================================================================================
/**
 * Entity for roomDB table.. And model class..
 */
//==================================================================================================
@Entity(tableName = "station")
data class Stations4RoomDB(
    val coordinateY: Double? = null,
    val coordinateX: Double? = null,
    var need: Int? = null,
    val name: String? = null,
    val stock: Int? = null,
    val capacity: Int? = null,
    var isHasCapacity: Boolean? = null,
    var isAddedFavorite: Boolean? = null,
    var distance: Int? = null
) {
    @PrimaryKey(autoGenerate = true)
    var favoriteStationId: Int? = null
}