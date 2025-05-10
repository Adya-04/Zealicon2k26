package com.gdg.zealicon2k25.domain.repository

import android.util.Log
import com.gdg.zealicon2k25.data.models.EnrollEventResponse
import com.gdg.zealicon2k25.data.models.EventsResponse
import com.gdg.zealicon2k25.data.remote.EventsApi
import com.gdg.zealicon2k25.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import java.net.SocketTimeoutException
import javax.inject.Inject


class EventsRepository @Inject constructor(private val eventsApi: EventsApi) {
    private val _events = MutableStateFlow<NetworkResult<EventsResponse>>(NetworkResult.Start())
    val events: StateFlow<NetworkResult<EventsResponse>>
        get() = _events

    private val _enrollEventState =
        MutableStateFlow<NetworkResult<EnrollEventResponse>>(NetworkResult.Start())
    val enrollEventState: StateFlow<NetworkResult<EnrollEventResponse>>
        get() = _enrollEventState


    suspend fun getEvents(accessToken: String, eventType: String) {
        _events.value = NetworkResult.Loading()
        try {
            Log.d("message1", "try block called")
            val response = eventsApi.getEvents(accessToken, eventType)
            Log.d("message1", "response success")
            Log.d("message1", response.body().toString())
            if (response.isSuccessful) {
                val responseBody = response.body()
                Log.d("message1", responseBody.toString())
                if (responseBody != null) {
                    Log.d("message1", response.toString())
                    _events.value = NetworkResult.Success(responseBody)
                } else {
                    _events.value = NetworkResult.Error("Response body is null")
                }
            } else if (response.errorBody() != null) {
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                Log.d("message1", errObj.toString())
                _events.value = NetworkResult.Error(errObj.getString("message"))
            } else {
                Log.d("message12", "Something went wrong")
                _events.value = NetworkResult.Error("Something went wrong")
            }
        } catch (e: SocketTimeoutException) {
            Log.d("message1", e.toString())
            _events.value = NetworkResult.Error("Please try again!")
        } catch (e: Exception) {
            Log.d("message12", e.toString())
            _events.value = NetworkResult.Error("Unexpected error occurred")
        }
    }

    suspend fun enrollEvent(token:String) {
        _enrollEventState.value = NetworkResult.Loading()
        try {
            Log.d("message1", "try block called")
            val response = eventsApi.enrollEvent(token)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _enrollEventState.value = NetworkResult.Success(responseBody)
                } else {
                    _enrollEventState.value = NetworkResult.Error("Response body is null")
                }
            } else if (response.errorBody() != null) {
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _enrollEventState.value = NetworkResult.Error(errObj.getString("message"))
            } else {
                _enrollEventState.value = NetworkResult.Error("Something went wrong")
            }
        } catch (e: SocketTimeoutException) {
            Log.d("message1", e.toString())
            _enrollEventState.value = NetworkResult.Error("Please try again!")
        } catch (e: Exception) {
            Log.d("message12", e.toString())
            _enrollEventState.value = NetworkResult.Error("Unexpected error occurred")
        }
    }

}