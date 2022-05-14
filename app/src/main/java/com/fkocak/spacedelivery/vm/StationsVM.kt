package com.fkocak.spacedelivery.vm

import com.fkocak.spacedelivery.base.BaseVM
import com.fkocak.spacedelivery.coroutines.AppCoroutines
import com.fkocak.spacedelivery.coroutines.SpaceDeliveryCoroutineDispatcherProvider
import com.fkocak.spacedelivery.utils.ApiStateView
import com.fkocak.spacedelivery.data.repositories.SpaceDeliveryRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StationsVM
@Inject
constructor(private val spaceDeliveryRepositories: SpaceDeliveryRepositories) : BaseVM() {

    private var isRunning = false

    init {
        if (!isRunning) {
            isRunning = true
            state.value = ApiStateView.Loading(true)
            AppCoroutines.io {
                Timber.i("Working on ---> ${Thread.currentThread().name} & trigger allStation api call..")
                getAllStations()
            }
        }
    }

    //==============================================================================================
    /**
     * Get AllStations Data from Repository Class..
     */
    //==============================================================================================
    private suspend fun getAllStations() {
        spaceDeliveryRepositories.getAllStations { allStation, error ->
            Timber.i("Response -- $allStation")
            withContext(SpaceDeliveryCoroutineDispatcherProvider.Main()) {
                Timber.i("Working on ---> ${Thread.currentThread().name} & handle data and check for null..")
                allStation?.let {
                    state.value = ApiStateView.Loading(boolean = false)
                    state.value = ApiStateView.Success(it)
                } ?: run {
                    state.value = ApiStateView.Error(error = error)
                }
            }
        }
    }

    //==============================================================================================
    /**
     * Save Space Ship Info.. (ShipName, Durability, Speed, Capacity)
     */
    //==============================================================================================
    fun saveSpaceShipInfo(shipName: String, durability: Int, speed: Int, capacity: Int) {
        spaceDeliveryRepositories.saveSpaceShipInfo(shipName, durability, speed, capacity) {
            Timber.i("Working on ---> ${Thread.currentThread().name} & handle insertion shipsInfo..")
            resultOfInsertShipsInfo.value = it
        }
    }
}
