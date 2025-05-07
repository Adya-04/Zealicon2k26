package com.gdg.zealicon2k25.data.models

data class VerifyOtpResponse(
    val success: Boolean,
    val message: String,
    val init_token:String
)
