package com.fkocak.spacedelivery.vm

import com.fkocak.spacedelivery.base.BaseVM
import com.fkocak.spacedelivery.coroutines.AppCoroutines
import com.fkocak.spacedelivery.coroutines.SpaceDeliveryCoroutineDispatcherProvider
import com.fkocak.spacedelivery.data.repositories.SpaceDeliveryRepositories
import com.fkocak.spacedelivery.utils.ApiStateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StationsVM
@Inject
constructor(private val spaceDeliveryRepositories: SpaceDeliveryRepositories) : BaseVM() {

    //==============================================================================================
    /**
     * Get AllStations Data from Repository Class..
     */
    //==============================================================================================
    fun getAllStationsFromApi() {
        AppCoroutines.io {
            spaceDeliveryRepositories.getAllStations { allStation, error ->
                Timber.i("Response -- $allStation")
                withContext(SpaceDeliveryCoroutineDispatcherProvider.Main()) {
                    Timber.i("Working on ---> ${Thread.currentThread().name} & handle data and check for null..")
                    allStation?.let {
                        sAllStationDataResult.value = ApiStateView.Loading(boolean = false)
                        sAllStationDataResult.value = ApiStateView.Success(it)
                    } ?: run {
                        sAllStationDataResult.value = ApiStateView.Error(error = error)
                    }
                }
            }
        }
    }

    //==============================================================================================
    /**
     * Get AllStations Data from Repository Class..
     */
    //==============================================================================================
    fun getAllStationsFromRoomDB() {
        AppCoroutines.io {
            val listOfAllStation = spaceDeliveryRepositories.fetchStationsFromCached()
            withContext(SpaceDeliveryCoroutineDispatcherProvider.Main()) {
                if (listOfAllStation.isNotEmpty())
                    sAllStationDataResultFromDB.value = ApiStateView.Success(listOfAllStation)
                else
                    sAllStationDataResultFromDB.value =
                        ApiStateView.Error("All Stations data reading but data is empty..!")
            }
        }
    }

    //==============================================================================================
    /**
     * Save Space Ship Info.. (ShipName, Durability, Speed, Capacity)
     */
    //==============================================================================================
    fun saveSpaceShipInfo(
        shipName: String,
        durability: Int,
        speed: Int,
        capacity: Int,
    ) {
        AppCoroutines.io {
            spaceDeliveryRepositories.saveSpaceShipInfo(shipName, durability, speed, capacity) {
                Timber.i("Working on ---> ${Thread.currentThread().name} & handle insertion shipsInfo..")
                withContext(SpaceDeliveryCoroutineDispatcherProvider.Main()) {
                    resultOfInsertShipsInfo.value = it
                }
            }
        }
    }

    //==============================================================================================
    /**
     * Get Space Ship Info.. (ShipName, Durability, Speed, Capacity)
     */
    //==============================================================================================
    fun getSpaceShipInfoFromRoomDB() {
        AppCoroutines.io {
            val shipsInfo = spaceDeliveryRepositories.fetchShipInfo()
            withContext(SpaceDeliveryCoroutineDispatcherProvider.Main()) {
                shipsInfo?.let {
                    shipInfoState.value = ApiStateView.Success(it)
                } ?: run {
                    shipInfoState.value =
                        ApiStateView.Error("Ship Info reading but data is null..!")
                }
            }
        }
    }
}
