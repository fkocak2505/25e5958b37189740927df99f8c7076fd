package com.fkocak.spacedelivery.data.repositories

import com.fkocak.spacedelivery.base.BaseApiResponse
import com.fkocak.spacedelivery.data.apiService.ApiService
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.room.SpaceDeliveryDAO
import com.fkocak.spacedelivery.utils.ApiState
import timber.log.Timber
import javax.inject.Inject

//==================================================================================================
/**
 * Repository Pattern is working in there. This class responsibility is returned data from api or
 * first returned roomDb data and after return api data..
 */
//==================================================================================================
class SpaceDeliveryRepositories @Inject
constructor(
    private val apiService: ApiService,
    private val spaceDeliveryDAO: SpaceDeliveryDAO
) : BaseApiResponse() {

    //==============================================================================================
    /**
     * Get AllStation data..
     */
    //==============================================================================================
    suspend fun getAllStations(completion: suspend (MutableList<Response4Stations>?, String?) -> Unit) {
        Timber.i("Working on ---> ${Thread.currentThread().name} & triggered getAllStations Repositories..")
        if (fetchStationsFromCached().isNotEmpty()) {
            Timber.i("Working on ---> ${Thread.currentThread().name} & handle data from room cached..")
            completion(fetchStationsFromCached(), null)
        }

        val listOfAllStation = apiCall { apiService.getStations() }

        isResponseNull(listOfAllStation) { data, error ->
            data?.let {
                Timber.i("Working on ---> ${Thread.currentThread().name} & handle data from api call..")
                spaceDeliveryDAO.deleteAllStation()
                spaceDeliveryDAO.insertAllStation(data)
                completion(data, null)
            } ?: run {
                Timber.i("Working on ---> ${Thread.currentThread().name} & handle error from api call..")
                completion(null, error)
            }

        }
    }

    //==============================================================================================
    /**
     * Fetch data from roomDB. If there is no data return emptyMutableList data..
     */
    //==============================================================================================
    private fun fetchStationsFromCached(): MutableList<Response4Stations> {
        return spaceDeliveryDAO.getAllStation()?.let {
            it.ifEmpty { mutableListOf() }
        } ?: run {
            mutableListOf()
        }
    }

    //==============================================================================================
    /**
     * Check ApiState data is null or not.
     */
    //==============================================================================================
    private suspend fun <T> isResponseNull(
        apiState: ApiState<T>,
        completion: suspend (T?, String?) -> Unit
    ) {
        apiState.data?.let {
            completion(it, null)
        } ?: run {
            completion(null, apiState.message)
        }
    }

}