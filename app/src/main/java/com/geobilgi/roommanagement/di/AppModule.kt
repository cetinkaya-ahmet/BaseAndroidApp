package com.geobilgi.roommanagement.di

import com.geobilgi.roommanagement.remote.RoomManagementApi
import com.geobilgi.roommanagement.remote.RoomManagementClient
import com.geobilgi.roommanagement.repository.RoomManagementRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRoomManagementRepository(
        api: RoomManagementApi
    ) = RoomManagementRepository(api)

    @Singleton
    @Provides
    fun provideRoomManagementApi(): RoomManagementApi {
        return RoomManagementClient.getInstance()
    }
}