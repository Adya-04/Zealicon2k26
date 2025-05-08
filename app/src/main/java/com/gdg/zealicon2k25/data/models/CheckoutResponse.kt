package com.gdg.zealicon2k25.data.models

data class CheckoutResponse(
    val message: String,
    val order: Order,
    val success: Boolean,
    val userDetails: UserDetails
)

data class Order(
    val amount: Int,
    val amount_due: Int,
    val amount_paid: Int,
    val attempts: Int,
    val created_at: Int,
    val currency: String,
    val entity: String,
    val id: String,
    val notes: Notes,
    val offer_id: Any,
    val receipt: Any,
    val status: String
)

data class UserDetails(
    val email: String,
    val name: String,
    val phone: Long
)

data class Notes(
    val order_type: String,
    val user_id: String
)