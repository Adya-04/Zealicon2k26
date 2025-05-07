package com.gdg.zealicon2k25.data.models

data class SignCloudinaryResponse(
    val success: Boolean,
    val message: String,
    val timestamp: Long,
    val folder: String,
    val public_id: String,
    val token: String
)
