package com.gdg.zealicon2k25.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.zealicon2k25.data.models.LoginRequest
import com.gdg.zealicon2k25.data.models.LoginResponse
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.OtpResponse
import com.gdg.zealicon2k25.data.models.VerifyOtpReq
import com.gdg.zealicon2k25.data.models.VerifyOtpResponse
import com.gdg.zealicon2k25.domain.repository.AuthRepository
import com.gdg.zealicon2k25.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel(){
    val otpResponseLiveData: StateFlow<NetworkResult<OtpResponse>>
        get() =authRepository.otpResponseLivedata

    val loginLiveData: StateFlow<NetworkResult<LoginResponse>>
        get() =authRepository.login

    val verifyOtpState: StateFlow<NetworkResult<VerifyOtpResponse>>
        get() = authRepository.verifyOtpResponse

    private var _email : String? = null
    val email : String? get() = _email
    fun setmail(value: String) {
        _email = value
        Log.d("message12345" , _email.toString())
    }

    fun getMail(): String{
        return email.toString()
    }

    fun getOtp(email: OtpRequest){
        viewModelScope.launch {
            Log.d("message1","View model called")
            authRepository.getOtp(email)
        }
    }

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            authRepository.login(loginRequest)
        }
    }

    fun verifyOtp(verifyOtpReq: VerifyOtpReq){
        viewModelScope.launch {
            Log.d("message1","View model called")
            authRepository.verifyOtp(verifyOtpReq)
        }
    }
}