package com.geobilgi.roommanagement.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RoomManagementClient {

    fun getInstance():RoomManagementApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("www.geobilgi.com/")
            .build()
            .create(RoomManagementApi::class.java)
    }
}