package com.fkocak.spacedelivery.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fkocak.spacedelivery.data.model.Response4Stations

//==================================================================================================
/**
 * Room DAO Class..
 */
//==============================================================================================
@Dao
interface SpaceDeliveryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStation(response4Stations: MutableList<Response4Stations>)

    @Query("DELETE FROM stations")
    suspend fun deleteAllStation()

    @Query("SELECT * FROM stations")
    fun getAllStation():  MutableList<Response4Stations>?

}