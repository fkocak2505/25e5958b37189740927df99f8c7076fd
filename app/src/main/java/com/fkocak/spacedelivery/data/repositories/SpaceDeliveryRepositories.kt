package com.fkocak.spacedelivery.data.repositories

import com.fkocak.spacedelivery.base.BaseApiResponse
import com.fkocak.spacedelivery.coroutines.SpaceDeliveryCoroutineDispatcherProvider
import com.fkocak.spacedelivery.data.apiService.ApiService
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.data.model.ShipInfo
import com.fkocak.spacedelivery.room.SpaceDeliveryDAO
import com.fkocak.spacedelivery.utils.ApiState
import com.fkocak.spacedelivery.utils.ApiStateView
import kotlinx.coroutines.withContext
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
//        if (fetchStationsFromCached().isNotEmpty()) {
//            Timber.i("Working on ---> ${Thread.currentThread().name} & handle data from room cached..")
//            completion(fetchStationsFromCached(), null)
//        }

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
    suspend fun saveSpaceShipInfo(
        shipName: String,
        durability: Int,
        speed: Int,
        capacity: Int,
        completion: suspend (ApiStateView) -> Unit
    ) {
        Timber.i("Working on ---> ${Thread.currentThread().name} & saved ships info..")
        spaceDeliveryDAO.deleteShipInfo()
        val shipsInfoPrimaryKey =
            spaceDeliveryDAO.insertShipsInfo(
                ShipInfo(
                    shipname = shipName,
                    durability = durability,
                    speed = speed,
                    capacity = capacity
                )
            )
        shipsInfoPrimaryKey?.let {
            completion(ApiStateView.Success(true))
        } ?: run {
            Timber.i("Error when inserting shipsInfo")
            completion(ApiStateView.Error("Error when inserting shipsInfo"))
        }
    }

    //==============================================================================================
    /**
     * Fetch data from roomDB. If there is no data return emptyMutableList data..
     */
    //==============================================================================================
    fun fetchStationsFromCached(): MutableList<Response4Stations> {
        return spaceDeliveryDAO.getAllStation()?.let {
            it.ifEmpty { mutableListOf() }
        } ?: run {
            mutableListOf()
        }
    }

    //==============================================================================================
    /**
     * Fetch shipInfo data from roomDB. If there is no data return null
     */
    //==============================================================================================
    fun fetchShipInfo(): ShipInfo? {
        return spaceDeliveryDAO.getShipInfo()?.let {
            it
        } ?: run {
            null
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