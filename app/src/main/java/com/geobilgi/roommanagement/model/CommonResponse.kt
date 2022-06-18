package com.geobilgi.roommanagement.model

data class CommonResponse(
    val code: Int,
    val `data`: Any,
    val errorResponse: ErrorResponse,
    val infoMessage: String
)