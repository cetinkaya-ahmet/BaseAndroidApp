package com.geobilgi.roommanagement.model

data class SettingsItem(
    val CreateDate: String,
    val CreatedBy: String,
    val DeleteDate: Any,
    val DeletedBy: Any,
    var Description: String?,
    val Group: Int,
    var Key: String,
    val ModifiedBy: String,
    val ModifiedDate: String,
    val UID: String,
    var Value: String
)