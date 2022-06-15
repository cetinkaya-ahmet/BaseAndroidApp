package com.geobilgi.roommanagement.ui.views.storyDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryDetailViewModel @Inject constructor(

) :ViewModel() {
    var isLoading = mutableStateOf(false)

    fun saveStorySettings(){
        isLoading.value = true
        viewModelScope.launch {
            delay(2000)
            isLoading.value = false
        }
    }

}