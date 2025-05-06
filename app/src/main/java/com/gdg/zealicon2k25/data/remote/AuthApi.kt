package com.gdg.zealicon2k25.data.remote

import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.OtpResponse
import com.gdg.zealicon2k25.data.models.VerifyOtpReq
import com.gdg.zealicon2k25.data.models.VerifyOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/send-otp")
    suspend fun getOtp(@Body email:OtpRequest): Response<OtpResponse>

    @POST("auth/verify-otp") //TO DO
    suspend fun verifyOtp(@Body verifyOtpReq: VerifyOtpReq): Response<VerifyOtpResponse>
}