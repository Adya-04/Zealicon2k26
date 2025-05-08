package com.gdg.zealicon2k25.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.zealicon2k25.data.models.LoginRequest
import com.gdg.zealicon2k25.data.models.LoginResponse
import com.gdg.zealicon2k25.data.models.LoginVerifyOtpResponse
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.OtpResponse
import com.gdg.zealicon2k25.data.models.SignCloudinaryResponse
import com.gdg.zealicon2k25.data.models.SignupRequest
import com.gdg.zealicon2k25.data.models.SignupResponse
import com.gdg.zealicon2k25.data.models.VerifyOtpReq
import com.gdg.zealicon2k25.data.models.VerifyOtpResponse
import com.gdg.zealicon2k25.domain.repository.AuthRepository
import com.gdg.zealicon2k25.pref.PrefDatastore
import com.gdg.zealicon2k25.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefs: PrefDatastore
) : ViewModel() {
    val otpResponseLiveData: StateFlow<NetworkResult<OtpResponse>>
        get() = authRepository.otpResponseLivedata

    val loginLiveData: StateFlow<NetworkResult<LoginResponse>>
        get() = authRepository.login

    val verifyOtpState: StateFlow<NetworkResult<VerifyOtpResponse>>
        get() = authRepository.verifyOtpResponse

    val signCloudinaryFlowPhoto: StateFlow<NetworkResult<SignCloudinaryResponse>>
        get() = authRepository.signCloudinaryFlowPhoto

    val signCloudinaryFlowId: StateFlow<NetworkResult<SignCloudinaryResponse>>
        get() = authRepository.signCloudinaryFlowId

    private var _selfieImageSignature: SignCloudinaryResponse? = null
    val selfieImageSignature: SignCloudinaryResponse? get() = _selfieImageSignature

    private var _idImageSignature: SignCloudinaryResponse? = null
    val idImageSignature: SignCloudinaryResponse? get() = _idImageSignature

    val signupFlow: StateFlow<NetworkResult<SignupResponse>>
        get() = authRepository.signupFlow

    val resendState: StateFlow<NetworkResult<OtpResponse>>
        get() = authRepository.resendState

    val loginVerifyState: StateFlow<NetworkResult<LoginVerifyOtpResponse>>
        get() = authRepository.loginVerifyOtpState

    val initToken: Flow<String> = prefs.getToken()
    val accessToken: Flow<String> = prefs.getAccessToken()
    val refreshToken: Flow<String> = prefs.getRefreshToken()

    private var _email: String? = null
    val email: String? get() = _email

    private var _username: String = ""
    val username: String get() = _username

    private var _userPhone: Long = 0
    val userPhone: Long get() = _userPhone

    private var _isLogin: Boolean = false
    val isLogin: Boolean get() = _isLogin


    fun setSelfieSignature(value: SignCloudinaryResponse) {
        _selfieImageSignature = value
    }

    fun setIdSignature(value: SignCloudinaryResponse) {
        _selfieImageSignature = value
    }

    fun setLogin(value: Boolean) {
        _isLogin = value
    }


    fun setmail(value: String) {
        _email = value
        Log.d("message12345", _email.toString())
    }

    fun setName(value: String) {
        _username = value
    }

    fun setPhone(value: Long) {
        _userPhone = value
    }

    fun getMail(): String {
        return email.toString()
    }

    fun getName(): String {
        return username
    }

    fun getPhone(): Long {
        return userPhone
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            prefs.saveToken(token)
        }
    }

    fun saveAccessToken(token: String) {
        viewModelScope.launch {
            prefs.saveAccessToken(token)
        }
    }

    fun saveRefreshToken(token: String) {
        viewModelScope.launch {
            prefs.saveRefreshToken(token)
        }
    }

    fun getOtp(email: OtpRequest) {
        viewModelScope.launch {
            Log.d("message1", "View model called")
            authRepository.getOtp(email)
        }
    }

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            authRepository.login(loginRequest)
        }
    }

    fun verifyOtp(verifyOtpReq: VerifyOtpReq) {
        viewModelScope.launch {
            Log.d("message1", "View model called")
            authRepository.verifyOtp(verifyOtpReq)
        }
    }

    fun loginVerifyOtp(verifyOtpReq: VerifyOtpReq){
        viewModelScope.launch {
            authRepository.loginVerifyOtp(verifyOtpReq)
        }
    }

    fun resendOtp(email: OtpRequest) {
        viewModelScope.launch {
            Log.d("message1", "View model called")
            authRepository.resendOtp(email)
        }
    }

    fun signCloudinaryPhoto(
        initToken: String
    ) {
        viewModelScope.launch {
            authRepository.signCloudinaryPhoto(initToken)
        }
    }

    fun signCloudinaryId(
        initToken: String
    ) {
        viewModelScope.launch {
            authRepository.signCloudinaryId(initToken)
        }
    }

    fun signup(
        signupRequest: SignupRequest,
        initToken: String
    ) {
        viewModelScope.launch {
            authRepository.signup(
                signupRequest = signupRequest,
                initToken = initToken
            )
        }
    }

    fun removeSignCloudinaryState() {
        authRepository.removeSignCloudinaryState()
    }
}
