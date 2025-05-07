package com.gdg.zealicon2k25.data.remote

import com.gdg.zealicon2k25.data.models.LoginRequest
import com.gdg.zealicon2k25.data.models.LoginResponse
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.OtpResponse
import com.gdg.zealicon2k25.data.models.SignCloudinaryResponse
import com.gdg.zealicon2k25.data.models.VerifyOtpReq
import com.gdg.zealicon2k25.data.models.VerifyOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @POST("auth/send-otp")
    suspend fun getOtp(@Body email:OtpRequest): Response<OtpResponse>

    @POST("auth/verify-otp")
    suspend fun verifyOtp(@Body verifyOtpReq: VerifyOtpReq): Response<VerifyOtpResponse>

    @POST("auth/login")
    suspend fun login( @Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/sign-cloudinary-token/{folder}")
    suspend fun getCloudinarySignature(
        @Header("Authorization") initToken: String,
        @Path("folder") folder: String
    ): Response<SignCloudinaryResponse>

    @PATCH("auth/resend-otp")
    suspend fun resendOtp(@Body otpRequest: OtpRequest): Response<OtpResponse>
}