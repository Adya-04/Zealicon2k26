package com.gdg.zealicon2k25.domain.repository

import android.util.Log
import com.gdg.zealicon2k25.data.models.MerchResponse
import com.gdg.zealicon2k25.data.remote.MerchApi
import com.gdg.zealicon2k25.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import java.net.SocketTimeoutException
import javax.inject.Inject

class MerchRepository @Inject constructor(private val merchApi: MerchApi) {
    private val _merchState = MutableStateFlow<NetworkResult<MerchResponse>>(NetworkResult.Start())
    val merchState: StateFlow<NetworkResult<MerchResponse>>
        get() = _merchState

    suspend fun getMerch(accessToken: String){
        _merchState.value = NetworkResult.Loading()
        try{
            val response = merchApi.getMerch(accessToken)
            Log.d("message123",response.body().toString())
            Log.d("message123*",response.toString())
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _merchState.value = NetworkResult.Success(responseBody)
                } else {
                    _merchState.value = NetworkResult.Error("Response body is null")
                }
            }else if(response.errorBody() != null){
                Log.d("message12", response.errorBody().toString())
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _merchState.value = NetworkResult.Error(errObj.getString("message"))
            }else{
                _merchState.value = NetworkResult.Error("Something went wrong")
            }
        }catch (e: SocketTimeoutException){
            Log.d("message1",e.toString())
            _merchState.value =NetworkResult.Error("Please try again!")
        }catch (e: Exception){
            Log.d("message12",e.toString())
            _merchState.value =NetworkResult.Error("Unexpected error occurred")
        }
    }
}