package com.fkocak.spacedelivery.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


//==================================================================================================
/**
 * Entity for roomDB table for ship info..
 */
//==================================================================================================
@Entity(tableName = "ship_info")
data class ShipInfo(
    val shipname: String,
    val durability: Int,
    val speed: Int,
    val capacity: Int
) {
    @PrimaryKey(autoGenerate = true)
    var shipInfoId: Int? = null
}