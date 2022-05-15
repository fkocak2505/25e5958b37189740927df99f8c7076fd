package com.fkocak.spacedelivery.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.data.model.ShipInfo
import com.fkocak.spacedelivery.data.model.Stations
import com.fkocak.spacedelivery.data.model.Stations4RoomDB

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteStation(favoriteStations: MutableList<Stations4RoomDB>): MutableList<Long>?

    @Query("DELETE FROM all_stations")
    suspend fun deleteAllStation()

    @Query("DELETE FROM ship_info")
    suspend fun deleteShipInfo()

    @Query("DELETE FROM station")
    suspend fun deleteFavoriteStation()

    @Query("SELECT * FROM all_stations")
    fun getAllStation(): MutableList<Response4Stations>?

    @Query("SELECT * FROM ship_info")
    fun getShipInfo(): ShipInfo?

    @Query("SELECT * FROM station")
    fun getFavoriteStationList(): MutableList<Stations4RoomDB>?


}