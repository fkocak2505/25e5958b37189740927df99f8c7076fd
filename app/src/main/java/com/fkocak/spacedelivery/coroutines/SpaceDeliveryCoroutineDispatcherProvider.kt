package com.fkocak.spacedelivery.coroutines

import kotlinx.coroutines.Dispatchers

object SpaceDeliveryCoroutineDispatcherProvider {

    fun IO() = Dispatchers.IO
    fun Main() = Dispatchers.Main

}