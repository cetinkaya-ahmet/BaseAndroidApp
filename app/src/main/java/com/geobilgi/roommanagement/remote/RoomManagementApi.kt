package com.geobilgi.roommanagement.remote

import com.geobilgi.roommanagement.model.*
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

    @POST("InsertTable/4D1C28A3-7CFA-44F0-B92F-3EAEFE4D71CC")
    suspend fun startNewSession(
        @Body() body : StartNewSessionRequest
    ): CommonResponse

    @GET("GetTable/vw_ActiveSession")
    suspend fun getActiveSession(): GetActiveSessionResponse

    @POST("InsertTable/41840ECE-C7E9-439D-91BE-7A61EA398C06")
    suspend fun createNewCartBySession(
        @Body() body : CreateNewCartRequest
    ): CommonResponse
}