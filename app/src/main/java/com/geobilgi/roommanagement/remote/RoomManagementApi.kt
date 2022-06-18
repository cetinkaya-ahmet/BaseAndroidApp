package com.geobilgi.roommanagement.remote

import com.geobilgi.roommanagement.model.CommonResponse
import com.geobilgi.roommanagement.model.SettingsResponse
import com.geobilgi.roommanagement.model.UpdateSettingsRequest
import retrofit2.http.*

interface RoomManagementApi {

    @GET("GetTable/EnvironmentSettings?query=[Group]|0")
    suspend fun getRoomSettings(): SettingsResponse

    @GET("GetTable/EnvironmentSettings")
    suspend fun getGameSettings(
        @Query("query") query : String
    ): SettingsResponse

    @PUT("UpdateTable/59DCE647-16A5-4C8F-96E6-02BDA79000F4")
    suspend fun updateSettings(
            @Body() body : UpdateSettingsRequest
    ): CommonResponse

}