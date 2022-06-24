package com.geobilgi.roommanagement.model

data class CreateNewCartRequestItem(
    val CreatedBy: String,
    val GameSessionUID: String,
    val Status: String
)