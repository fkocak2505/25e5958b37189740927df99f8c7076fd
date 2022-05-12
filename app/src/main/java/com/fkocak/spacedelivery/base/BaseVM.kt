package com.fkocak.spacedelivery.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fkocak.spacedelivery.utils.ApiStateView
import kotlinx.coroutines.Job

abstract class BaseVM : ViewModel() {

    /**
     * Using State for handle data, error or loading status inside composable function..
     */
    val state: MutableState<ApiStateView> = mutableStateOf(ApiStateView.Loading(false))


    /**
     * Job: Controlling for manuel coroutine dispatchers..
     */
    var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}