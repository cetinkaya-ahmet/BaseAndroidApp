package com.geobilgi.roommanagement.repository

import com.geobilgi.roommanagement.remote.RoomManagementApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class RoomManagementRepository @Inject constructor(
    private val api: RoomManagementApi
) {
}