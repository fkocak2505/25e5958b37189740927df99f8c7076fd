package com.fkocak.spacedelivery.utils

//==================================================================================================
/**
 * ApiStateView for composable function..
 */
//==============================================================================================
sealed class ApiStateView {
    data class Loading(val boolean: Boolean = false) : ApiStateView()
    data class Success(val any: Any) : ApiStateView()
    data class Error(val error: String? = null) : ApiStateView()
}


//==================================================================================================
/**
 * ApiState for request api and handle data..
 */
//==============================================================================================
sealed class ApiState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class OnSuccess<T>(data: T) : ApiState<T>(data)
    class OnFail<T>(message: String, data: T? = null) : ApiState<T>(data, message)
}
