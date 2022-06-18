package com.geobilgi.roommanagement.ui.views.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geobilgi.roommanagement.model.SettingsItem
import com.geobilgi.roommanagement.repository.RoomManagementRepository
import com.geobilgi.roommanagement.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RoomManagementRepository
):ViewModel() {
    var isLoading = mutableStateOf(false)
    var roomSettings = mutableStateOf<List<SettingsItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var infoMessage = mutableStateOf("")

    init {
        getRoomSettings()
    }
    private fun getRoomSettings(){
        isLoading.value = true
        viewModelScope.launch {
            val result = repository.getRoomSettings()
            when(result) {
                is Resource.Success -> {
                    val items =  result.data!!.mapIndexed { index, item -> item }
                    roomSettings.value = items

                    errorMessage.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
            }

        }
    }

    fun updateSettings(settingsItem: SettingsItem){
       isLoading.value = true
        viewModelScope.launch {
            val result = repository.updateSetting(settingsItem)
            when(result) {
                is Resource.Success -> {
                    val items =  roomSettings.value.mapIndexed { index, item ->
                        if(item.UID.equals(settingsItem.UID,false)){
                            settingsItem
                        }else{
                            item
                        }
                    }
                    roomSettings.value = items
                    infoMessage.value = result.data?.infoMessage ?: "İşlem Başarılı"
                    errorMessage.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
            }
            //getRoomSettings()

        }
    }

    fun emergencyStart(){
        viewModelScope.launch {

        }
    }


}