package com.gdg.zealicon2k25.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageUploadApi {
    @Multipart
    @POST("v1_1/dzvns6dx6/image/upload")
    suspend fun uploadToCloudinary(
        @Part file: MultipartBody.Part,
        @Part("api_key") apiKey: RequestBody,
        @Part("timestamp") timestamp: RequestBody,
        @Part("signature") signature: RequestBody,
        @Part("public_id") publicId: RequestBody,
        @Part("folder") folder: RequestBody
    ): Response<ResponseBody>
}