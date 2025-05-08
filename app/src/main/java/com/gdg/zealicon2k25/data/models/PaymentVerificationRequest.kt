package com.gdg.zealicon2k25.data.models

data class PaymentVerificationRequest(
    val razorpay_order_id: String,
    val razorpay_payment_id: String,
    val razorpay_signature: String
)