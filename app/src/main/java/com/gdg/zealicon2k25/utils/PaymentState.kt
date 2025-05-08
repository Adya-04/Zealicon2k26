package com.gdg.zealicon2k25.utils

sealed class PaymentState {
    object Idle : PaymentState()
    object Success : PaymentState()
    object Error : PaymentState()

}