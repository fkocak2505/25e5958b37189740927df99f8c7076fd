package com.fkocak.spacedelivery.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

//==================================================================================================
/**
 * This object has 2 function. These is main and io threads for coroutines dispatchers..
 */
//==================================================================================================
object AppCoroutines {
    /**
     * Working on Main Thread (using for UI components or reactions..)
     */
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(SpaceDeliveryCoroutineDispatcherProvider.Main()).launch {
            try {
                work()
            } catch (E: Exception) {
                Timber.e("==== Some exception is in main thread -->" + E.message)
                E.printStackTrace()
            }
        }

    /**
     * Working on IO Thread (api calling)
     */
    fun io(work: suspend (() -> Unit)) =
        CoroutineScope(SpaceDeliveryCoroutineDispatcherProvider.IO()).launch {
            try {
                work()
            } catch (E: Exception) {
                Timber.e("==== Some exception is in io thread -->" + E.message)
                E.printStackTrace()
            }
        }
}