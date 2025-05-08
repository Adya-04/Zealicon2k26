package com.gdg.zealicon2k25.data.remote

import com.gdg.zealicon2k25.data.models.MerchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MerchApi {
    @GET("api/merch/get")
    suspend fun getMerch(@Header ("Authorization") token : String): Response<MerchResponse>
}