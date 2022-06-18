package com.geobilgi.roommanagement.remote

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RoomManagementClient {

    fun getInstance():RoomManagementApi{
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor {
            val original: Request = it.request()

            val request: Request = original.newBuilder()
                .header("XApiKey", "pgH7QzFHJx4w46fI~5Uzi4RvtTwlEXp")
                .method(original.method, original.body)
                .build()

            it.proceed(request)
        }
        return Retrofit.Builder()
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://apitest.geobilgi.net/Satem/SatemGeneric/api/DB/")
            .build()
            .create(RoomManagementApi::class.java)
    }

}
