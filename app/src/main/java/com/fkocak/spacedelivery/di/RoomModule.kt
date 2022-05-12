package com.fkocak.spacedelivery.di

import android.app.Application
import androidx.room.Room
import com.fkocak.spacedelivery.room.SpaceDeliveryDAO
import com.fkocak.spacedelivery.room.SpaceDeliveryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): SpaceDeliveryDatabase =
        Room.databaseBuilder(application, SpaceDeliveryDatabase::class.java, "SpaceDeliveryDB")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesDao(db: SpaceDeliveryDatabase): SpaceDeliveryDAO = db.getSpaceDeliveryDAO()

}