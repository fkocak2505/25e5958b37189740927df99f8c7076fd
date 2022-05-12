package com.fkocak.spacedelivery.room

import androidx.room.TypeConverter
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomsConvertor {

    //==============================================================================================
    /**
     * Convert String to Any type of data..
     */
    //==============================================================================================
    @TypeConverter
    fun convertStringToAny(data: String?): MutableList<Response4Stations> {
        val gson = Gson()
        if (data == null) {
            return mutableListOf()
        }
        val listType = object : TypeToken<MutableList<Response4Stations?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    //==============================================================================================
    /**
     * Convert Any type data to String
     */
    //==============================================================================================
    @TypeConverter
    fun convertAnyToString(myObjects: MutableList<Response4Stations?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

}