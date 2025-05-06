package com.gdg.zealicon2k25.data.remote

import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.OtpResponse
import com.gdg.zealicon2k25.data.models.SignCloudinaryResponse
import com.gdg.zealicon2k25.data.models.VerifyOtpReq
import com.gdg.zealicon2k25.data.models.VerifyOtpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface AuthApi {
    @POST("auth/send-otp")
    suspend fun getOtp(@Body email:OtpRequest): Response<OtpResponse>

    @POST("auth/verify-otp") //TO DO
    suspend fun verifyOtp(@Body verifyOtpReq: VerifyOtpReq): Response<VerifyOtpResponse>

    @GET("/sign-cloudinary-token/{folder}")
    suspend fun getCloudinarySignature(
        @Header("Authorization") initToken: String,
        @Path("folder") folder: String
    ): Response<SignCloudinaryResponse>
}