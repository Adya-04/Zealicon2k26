package com.gdg.zealicon2k25.domain.repository

import android.util.JsonToken
import android.util.Log
import android.util.Log.e
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdg.zealicon2k25.data.models.LoginRequest
import com.gdg.zealicon2k25.data.models.LoginResponse
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.OtpResponse
import com.gdg.zealicon2k25.data.models.SignCloudinaryResponse
import com.gdg.zealicon2k25.data.models.VerifyOtpReq
import com.gdg.zealicon2k25.data.models.VerifyOtpResponse
import com.gdg.zealicon2k25.data.remote.AuthApi
import com.gdg.zealicon2k25.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import java.net.SocketTimeoutException
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AuthApi) {
    private val _otpResponseLivedata = MutableStateFlow<NetworkResult<OtpResponse>>(NetworkResult.Start())
    val otpResponseLivedata: StateFlow<NetworkResult<OtpResponse>>
        get() =_otpResponseLivedata

    private val _verifyOtpLivedata = MutableStateFlow<NetworkResult<VerifyOtpResponse>>(NetworkResult.Start())
    val verifyOtpResponse: StateFlow<NetworkResult<VerifyOtpResponse>>
        get()=_verifyOtpLivedata

    private val _login = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Start())
    val login: StateFlow<NetworkResult<LoginResponse>>
        get()=_login

    private val _resendState= MutableStateFlow<NetworkResult<OtpResponse>>(NetworkResult.Start())
    val resendState: StateFlow<NetworkResult<OtpResponse>>
        get() = _resendState

    private val _signCloudinaryFlow = MutableStateFlow<NetworkResult<SignCloudinaryResponse>>(NetworkResult.Start())
    val signCloudinaryFlow: StateFlow<NetworkResult<SignCloudinaryResponse>> get() = _signCloudinaryFlow

    suspend fun getOtp(email: OtpRequest){
        _otpResponseLivedata.value = NetworkResult.Loading()
        try {
            Log.d("message1","try block called")
            val response = authApi.getOtp(email)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _otpResponseLivedata.value=NetworkResult.Success(responseBody)
                } else {
                    _otpResponseLivedata.value = NetworkResult.Error("Response body is null")
                }
            } else if (response.errorBody() != null) {
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _otpResponseLivedata.value =NetworkResult.Error(errObj.getString("message"))
            } else {
                _otpResponseLivedata.value =NetworkResult.Error("Something went wrong")
            }
        }catch (e: SocketTimeoutException){
            Log.d("message1",e.toString())
            _otpResponseLivedata.value =NetworkResult.Error("Please try again!")
        }catch (e: Exception){
            Log.d("message12",e.toString())
            _otpResponseLivedata.value =NetworkResult.Error("Unexpected error occurred")
        }
    }

    suspend fun login(loginRequest: LoginRequest){
        _login.value =(NetworkResult.Loading())
        try{
            Log.d("message123","try block")
            val response = authApi.login(loginRequest)
            if (response.isSuccessful) {
                Log.d("message123","try block success")
                val responseBody = response.body()
                if (responseBody != null) {
                    Log.d("message123","$responseBody")
                    _login.value =(NetworkResult.Success(responseBody))
                } else {
                    _login.value =(NetworkResult.Error("Response body is null"))
                }
            } else if (response.errorBody() != null) {
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                Log.d("message12", errObj.toString())
                if(errObj.getString("message").equals("User not found!")){
                }
                _login.value =(NetworkResult.Error(errObj.getString("message")))
            } else {
                Log.d("message123",response.errorBody().toString())
                _login.value =(NetworkResult.Error("Something went wrong"))
            }
        }catch (e: SocketTimeoutException){
            Log.d("message123",e.toString())
            _login.value =(NetworkResult.Error("Please try again!"))
        }catch (e: Exception){
            Log.d("message123",e.toString())
            _login.value =(NetworkResult.Error("Unexpected error occurred"))
        }
    }

    suspend fun verifyOtp(otp: VerifyOtpReq){
        _verifyOtpLivedata.value =(NetworkResult.Loading())
        try {
            Log.d("message1","try block called")
            val response = authApi.verifyOtp(otp)
            if (response.isSuccessful) {
                Log.d("message123","try block success")
                val responseBody = response.body()
                if (responseBody != null) {
                    Log.d("message123","$responseBody")
                    _verifyOtpLivedata.value=(NetworkResult.Success(responseBody))
                } else {
                    Log.d("message123",response.errorBody().toString())
                    _verifyOtpLivedata.value=(NetworkResult.Error("Response body is null"))
                }
            } else if (response.errorBody() != null) {
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                Log.d("message12", errObj.toString())
                _verifyOtpLivedata.value=(NetworkResult.Error(errObj.getString("message")))
            } else {
                _verifyOtpLivedata.value=(NetworkResult.Error("Something went wrong"))
            }
        }catch (e: SocketTimeoutException){
            Log.d("message1",e.toString())
            _verifyOtpLivedata.value=(NetworkResult.Error("Please try again!"))
        }catch (e: Exception){
            Log.d("message12",e.toString())
            _verifyOtpLivedata.value=(NetworkResult.Error("Unexpected error occurred"))
        }
    }

    suspend fun signCloudinaryFlow(folder: String, initToken:String){
        _signCloudinaryFlow.value = NetworkResult.Loading()
        try{
            val response = authApi.getCloudinarySignature(
                folder = folder,
                initToken = initToken
            )
            if (response.isSuccessful) {
                Log.d("message123","try block success")
                val responseBody = response.body()
                if (responseBody != null) {
                    Log.d("message123","$responseBody")
                    _signCloudinaryFlow.value=(NetworkResult.Success(responseBody))
                } else {
                    Log.d("message123",response.errorBody().toString())
                    _signCloudinaryFlow.value=(NetworkResult.Error("Response body is null"))
                }
            } else if (response.errorBody() != null) {
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                Log.d("message12", errObj.toString())
                _signCloudinaryFlow.value=(NetworkResult.Error(errObj.getString("message")))
            } else {
                _signCloudinaryFlow.value=(NetworkResult.Error("Something went wrong"))
            }
        }catch (e: SocketTimeoutException){
            Log.d("message1",e.toString())
            _signCloudinaryFlow.value=(NetworkResult.Error("Please try again!"))
        }catch (e: Exception){
            Log.d("message12",e.toString())
            _signCloudinaryFlow.value=(NetworkResult.Error("Unexpected error occurred"))
        }
    }

    suspend fun resendOtp(email: OtpRequest){
        _resendState.value = NetworkResult.Loading()
        try {
            Log.d("message1","try block called")
            val response = authApi.resendOtp(email)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _resendState.value=NetworkResult.Success(responseBody)
                } else {
                    _resendState.value = NetworkResult.Error("Response body is null")
                }
            } else if (response.errorBody() != null) {
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _resendState.value =NetworkResult.Error(errObj.getString("message"))
            } else {
                _resendState.value =NetworkResult.Error("Something went wrong")
            }
        }catch (e: SocketTimeoutException){
            Log.d("message1",e.toString())
            _resendState.value =NetworkResult.Error("Please try again!")
        }catch (e: Exception){
            Log.d("message12",e.toString())
            _resendState.value =NetworkResult.Error("Unexpected error occurred")
        }
    }
}