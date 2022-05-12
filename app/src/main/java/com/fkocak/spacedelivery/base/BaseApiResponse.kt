package com.fkocak.spacedelivery.base

import com.fkocak.spacedelivery.utils.ApiState
import retrofit2.Response
import timber.log.Timber

abstract class BaseApiResponse {

    //==============================================================================================
    /**
     * Base Api Call suspend function..
     */
    //==============================================================================================
    suspend fun <T> apiCall(apiCall: suspend () -> Response<T>): ApiState<T> {
        try {
            Timber.i("Working on ---> ${Thread.currentThread().name} & request to api soon inside baseApi..")
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ApiState.OnSuccess(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    //==============================================================================================
    /**
     * Error method if response code is not 200..
     */
    //==============================================================================================
    private fun <T> error(errorMessage: String): ApiState<T> =
        ApiState.OnFail(" === Request is failed ---> $errorMessage")
}