package com.gdg.zealicon2k25.data.models

data class MerchCheckoutRequest(
    val merch_id: String,
    val quantity: Int,
    val size: String
)