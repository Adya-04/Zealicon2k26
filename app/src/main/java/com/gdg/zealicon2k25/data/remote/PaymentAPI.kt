package com.gdg.zealicon2k25.data.remote

import com.gdg.zealicon2k25.data.models.CheckoutResponse
import com.gdg.zealicon2k25.data.models.GetZealResponse
import com.gdg.zealicon2k25.data.models.PaymentVerificationRequest
import com.gdg.zealicon2k25.data.models.PaymentVerificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PaymentAPI {
    @POST("api/zeal/checkout")
    suspend fun checkout(
        @Header("Authorization") accessToken: String
    ): Response<CheckoutResponse>

    @POST("api/zeal/payment-verification")
    suspend fun paymentVerify(
        @Header("Authorization") accessToken: String,
        @Body paymentVerificationRequest: PaymentVerificationRequest
    ): Response<PaymentVerificationResponse>

    @GET("api/zeal/get-zeal-id")
    suspend fun getZealId(
        @Header("Authorization") accessToken: String
    ): Response<GetZealResponse>
}