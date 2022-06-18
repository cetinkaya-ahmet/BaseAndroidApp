package com.geobilgi.roommanagement.model

data class UpdateSettingsRequestItem(
    val Description: String,
    val Group: String,
    val Key: String,
    val ModifiedBy: String,
    val UID: String,
    val Value: String
)