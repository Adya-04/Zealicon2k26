package com.gdg.zealicon2k25.data.models

data class MerchResponse(
    val merch: List<Merch>,
    val message: String,
    val success: Boolean
)