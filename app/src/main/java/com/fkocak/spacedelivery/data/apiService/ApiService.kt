package com.fkocak.spacedelivery.data.apiService

import com.fkocak.spacedelivery.data.model.Response4Stations
import retrofit2.Response
import retrofit2.http.GET

//==================================================================================================
/**
 * Interface for Retrofit endpoint path for requesting..
 */
//==================================================================================================
interface ApiService {

    @GET("v3/e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    suspend fun getStations(): Response<MutableList<Response4Stations>>

}