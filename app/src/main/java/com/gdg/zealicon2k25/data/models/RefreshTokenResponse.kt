package com.gdg.zealicon2k25.data.models

data class RefreshTokenResponse(
    val access_token: String,
    val message: String,
    val success: Boolean
)