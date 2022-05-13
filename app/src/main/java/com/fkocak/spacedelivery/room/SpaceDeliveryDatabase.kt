package com.fkocak.spacedelivery.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fkocak.spacedelivery.data.model.Response4Stations

//==================================================================================================
/**
 * Room Database Class. @Database annotation has some params. If you update data inside table,
 * you can increate @version for this..
 */
//==============================================================================================
@TypeConverters(RoomsConvertor::class)
@Database(entities = [Response4Stations::class], version = 1, exportSchema = false)
abstract class SpaceDeliveryDatabase : RoomDatabase()  {

    abstract fun getSpaceDeliveryDAO(): SpaceDeliveryDAO

}