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
    @POST("api/auth/send-otp")
    suspend fun getOtp(@Body email:OtpRequest): Response<OtpResponse>

    @POST("api/auth/verify-otp")
    suspend fun verifyOtp(@Body verifyOtpReq: VerifyOtpReq): Response<VerifyOtpResponse>

    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("api/auth/sign-cloudinary-token/idCard")
    suspend fun getCloudinarySignatureIdCard(
        @Header("Authorization") initToken: String,
    ): Response<SignCloudinaryResponse>

    @GET("api/auth/sign-cloudinary-token/photo")
    suspend fun getCloudinarySignaturePhoto(
        @Header("Authorization") initToken: String,
    ): Response<SignCloudinaryResponse>

    @POST("api/auth/signup")
    suspend fun signup(
        @Header("Authorization") initToken: String,
        @Body signupRequest: SignupRequest
    ):Response<SignupResponse>

    @PATCH("api/auth/resend-otp")
    suspend fun resendOtp(@Body otpRequest: OtpRequest): Response<OtpResponse>
}