package com.gdg.zealicon2k25.data.models

data class SignupResponse(
    val access_token: String,
    val message: String,
    val refresh_token: String,
    val success: Boolean
)