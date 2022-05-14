package com.fkocak.spacedelivery.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fkocak.spacedelivery.data.model.ShipInfo
import com.fkocak.spacedelivery.utils.ApiStateView
import kotlinx.coroutines.Job

abstract class BaseVM : ViewModel() {

    /**
     * Using State for handle data from station Api
     */
    val sAllStationDataResult: MutableState<ApiStateView> = mutableStateOf(ApiStateView.Loading(false))

    /**
     * Using State for handle data from station RoomDB
     */
    val sAllStationDataResultFromDB: MutableState<ApiStateView> = mutableStateOf(ApiStateView.Loading(false))

    /**
     * Using State for handle shipsData is inserted successfully
     */
    val resultOfInsertShipsInfo: MutableState<ApiStateView> =
        mutableStateOf(ApiStateView.Loading(false))

    /**
     * Using State for handle shipsData from read roomDB
     */
    val shipInfoState: MutableState<ApiStateView> = mutableStateOf(ApiStateView.Loading(false))


    /**
     * Job: Controlling for manuel coroutine dispatchers..
     */
    var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}