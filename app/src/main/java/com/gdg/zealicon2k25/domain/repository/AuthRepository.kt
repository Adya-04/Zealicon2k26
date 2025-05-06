package com.gdg.zealicon2k25.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.OtpResponse
import com.gdg.zealicon2k25.data.models.VerifyOtpResponse
import com.gdg.zealicon2k25.data.remote.AuthApi
import com.gdg.zealicon2k25.utils.NetworkResult
import org.json.JSONObject
import java.net.SocketTimeoutException
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AuthApi) {
    private val _otpResponseLivedata = MutableLiveData<NetworkResult<OtpResponse>>()
    val otpResponseLivedata: LiveData<NetworkResult<OtpResponse>>
        get() =_otpResponseLivedata

    private val _verifyOtpLivedata = MutableLiveData<NetworkResult<VerifyOtpResponse>>()
    val verifyOtpResponse: LiveData<NetworkResult<VerifyOtpResponse>>
        get()=_verifyOtpLivedata

    suspend fun getOtp(email: OtpRequest){
        _otpResponseLivedata.postValue(NetworkResult.Loading())
        try {
            Log.d("message1","try block called")
            val response = authApi.getOtp(email)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _otpResponseLivedata.postValue(NetworkResult.Success(responseBody))
                } else {
                    _otpResponseLivedata.postValue(NetworkResult.Error("Response body is null"))
                }
            } else if (response.errorBody() != null) {
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _otpResponseLivedata.postValue(NetworkResult.Error(errObj.getString("message")))
            } else {
                _otpResponseLivedata.postValue(NetworkResult.Error("Something went wrong"))
            }
        }catch (e: SocketTimeoutException){
            Log.d("message1",e.toString())
            _otpResponseLivedata.postValue(NetworkResult.Error("Please try again!"))
        }catch (e: Exception){
            Log.d("message12",e.toString())
            _otpResponseLivedata.postValue(NetworkResult.Error("Unexpected error occurred"))
        }

    }


}