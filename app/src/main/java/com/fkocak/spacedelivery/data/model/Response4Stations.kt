package com.fkocak.spacedelivery.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//==================================================================================================
/**
 * Entity for roomDB table.. And model class..
 */
//==================================================================================================
@Entity(tableName = "stations")
data class Response4Stations(

    @field:SerializedName("coordinateY")
    val coordinateY: Double? = null,

    @field:SerializedName("coordinateX")
    val coordinateX: Double? = null,

    @field:SerializedName("need")
    val need: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("stock")
    val stock: Int? = null,

    @field:SerializedName("capacity")
    val capacity: Int? = null
) {
    @PrimaryKey(autoGenerate = true)
    var stationId: Int? = null
}
