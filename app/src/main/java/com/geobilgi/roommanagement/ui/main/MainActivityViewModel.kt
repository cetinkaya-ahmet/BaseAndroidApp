package com.geobilgi.roommanagement.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geobilgi.roommanagement.repository.RoomManagementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: RoomManagementRepository
): ViewModel() {


}