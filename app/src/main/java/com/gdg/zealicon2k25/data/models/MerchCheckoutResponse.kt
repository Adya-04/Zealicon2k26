package com.gdg.zealicon2k25.data.models

data class MerchCheckoutResponse(
    val message: String,
    val order: MerchOrder,
    val success: Boolean
)
data class MerchNotes(
    val merch_id: String,
    val order_type: String,
    val quantity: String
)

data class MerchOrder(
    val amount: Int,
    val amount_due: Int,
    val amount_paid: Int,
    val attempts: Int,
    val created_at: Int,
    val currency: String,
    val entity: String,
    val id: String,
    val notes: MerchNotes,
    val offer_id: Any,
    val receipt: Any,
    val status: String
)