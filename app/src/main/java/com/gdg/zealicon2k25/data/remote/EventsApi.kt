package com.gdg.zealicon2k25.data.remote

import com.gdg.zealicon2k25.data.models.EnrollEventResponse
import com.gdg.zealicon2k25.data.models.EventsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface EventsApi {
    @GET("/api/events/get")
    suspend fun getEvents(
        @Header("Authorization") accessToken: String,
        @Query("event_type") eventType: String
    ): Response<EventsResponse>

    @POST("/api/events/enroll")
    suspend fun enrollEvent(@Header("Authorization") token:String): Response<EnrollEventResponse>
}