package com.fkocak.spacedelivery.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.data.model.ShipInfo

//==================================================================================================
/**
 * Room DAO Class..
 */
//==============================================================================================
@Dao
interface SpaceDeliveryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStation(response4Stations: MutableList<Response4Stations>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShipsInfo(shipsInfo: ShipInfo): Long?

    @Query("DELETE FROM stations")
    suspend fun deleteAllStation()

    @Query("DELETE FROM ship_info")
    suspend fun deleteShipInfo()

    @Query("SELECT * FROM stations")
    fun getAllStation(): MutableList<Response4Stations>?

    @Query("SELECT * FROM ship_info")
    fun getShipInfo(): ShipInfo?


}