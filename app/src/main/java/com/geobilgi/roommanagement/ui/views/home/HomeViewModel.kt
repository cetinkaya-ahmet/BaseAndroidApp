package com.geobilgi.roommanagement.ui.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

):ViewModel() {

    fun emergencyStart(){
        viewModelScope.launch {

        }
    }
}