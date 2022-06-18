package com.geobilgi.roommanagement.ui.views.storyDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geobilgi.roommanagement.model.SettingsItem
import com.geobilgi.roommanagement.repository.RoomManagementRepository
import com.geobilgi.roommanagement.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryDetailViewModel @Inject constructor(
    var repository: RoomManagementRepository
) :ViewModel() {
    var isLoading = mutableStateOf(false)
    var gameSettings = mutableStateOf<List<SettingsItem>>(listOf())
    var errorMessage = mutableStateOf("")


    fun getGameSettings(gameId : String){
        isLoading.value = true
        viewModelScope.launch {
            val result = repository.getGameSettings(gameId)
            when(result) {
                is Resource.Success -> {
                    val items =  result.data!!.mapIndexed { index, item -> item }
                    gameSettings.value = items

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
            val items =  gameSettings.value.mapIndexed { index, item ->
                if(item.UID.equals(settingsItem.UID,false)){
                    settingsItem
                }else{
                    item
                }
            }
            gameSettings.value = items
            errorMessage.value = ""
            isLoading.value = false

        }
    }

    fun saveStorySettings(){
        isLoading.value = true
        viewModelScope.launch {
            val result = repository.updateStorySettings(gameSettings.value)
            when(result) {
                is Resource.Success -> {
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

}