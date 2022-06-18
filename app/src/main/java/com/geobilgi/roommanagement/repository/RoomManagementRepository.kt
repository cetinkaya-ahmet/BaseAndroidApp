package com.geobilgi.roommanagement.repository

import com.geobilgi.roommanagement.model.*
import com.geobilgi.roommanagement.remote.RoomManagementApi
import com.geobilgi.roommanagement.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.util.*
import javax.inject.Inject

@ActivityScoped
class RoomManagementRepository @Inject constructor(
    private val api: RoomManagementApi
) {
    suspend fun getRoomSettings(): Resource<SettingsResponse> {
        val response = try {
            api.getRoomSettings()
        } catch(e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getGameSettings(storyId : String): Resource<SettingsResponse> {
        val response = try {
            api.getGameSettings(query = "[Group]|$storyId")
        } catch(e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun updateSetting(settingsItem : SettingsItem): Resource<CommonResponse> {
        val response = try {
            val body = UpdateSettingsRequest()
            body.add(UpdateSettingsRequestItem(
                Description = settingsItem.Description ?: "",
                Group = settingsItem.Group.toString(),
                Key = settingsItem.Key,
                ModifiedBy = UUID.fromString("00000000-0000-0000-0000-000000000000").toString(),
                UID = settingsItem.UID,
                Value = settingsItem.Value
            ))
            api.updateSettings(body = body)
        } catch(e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun updateStorySettings(settingsItemList: List<SettingsItem>): Resource<CommonResponse> {
        val response = try {
            val body = UpdateSettingsRequest()
            settingsItemList.forEach {
                body.add(UpdateSettingsRequestItem(
                    Description = it.Description ?: "",
                    Group = it.Group.toString(),
                    Key = it.Key,
                    ModifiedBy = UUID.fromString("00000000-0000-0000-0000-000000000000").toString(),
                    UID = it.UID,
                    Value = it.Value
                ))
            }
            api.updateSettings(body = body)
        } catch(e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }
}