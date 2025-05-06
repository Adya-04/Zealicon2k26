package com.gdg.zealicon2k25.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.OtpResponse
import com.gdg.zealicon2k25.domain.repository.AuthRepository
import com.gdg.zealicon2k25.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel(){
    val otpResponseLiveData: LiveData<NetworkResult<OtpResponse>>
        get() =authRepository.otpResponseLivedata

    fun getOtp(email: OtpRequest){
        viewModelScope.launch {
            Log.d("message1","View model called")
            authRepository.getOtp(email)
        }
    }
}