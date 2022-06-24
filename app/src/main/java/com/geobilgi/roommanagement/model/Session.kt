package com.geobilgi.roommanagement.model

data class Session(
    val CreateDate: String,
    val CreatedBy: String,
    val DeleteDate: Any,
    val DeletedBy: Any,
    val GameGroup: Int,
    val ModifiedBy: Any,
    val ModifiedDate: Any,
    val Status: Int,
    val UID: String
)