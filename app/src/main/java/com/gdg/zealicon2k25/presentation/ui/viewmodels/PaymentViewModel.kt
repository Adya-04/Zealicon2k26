package com.gdg.zealicon2k25.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.zealicon2k25.data.models.CheckoutResponse
import com.gdg.zealicon2k25.data.models.GetZealResponse
import com.gdg.zealicon2k25.data.models.MerchCheckoutRequest
import com.gdg.zealicon2k25.data.models.MerchCheckoutResponse
import com.gdg.zealicon2k25.data.models.PaymentVerificationRequest
import com.gdg.zealicon2k25.data.models.PaymentVerificationResponse
import com.gdg.zealicon2k25.domain.repository.PaymentRepository
import com.gdg.zealicon2k25.pref.PrefDatastore
import com.gdg.zealicon2k25.utils.NetworkResult
import com.gdg.zealicon2k25.utils.PaymentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel@Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val prefs: PrefDatastore
) : ViewModel() {
    val checkoutState: StateFlow<NetworkResult<CheckoutResponse>> get() = paymentRepository.checkoutState
    val paymentVerifyState: StateFlow<NetworkResult<PaymentVerificationResponse>> get() = paymentRepository.paymentVerifyState
    val getZealState: StateFlow<NetworkResult<GetZealResponse>> get() = paymentRepository.getZealIdState
    val merchCheckoutState: StateFlow<NetworkResult<MerchCheckoutResponse>> get() = paymentRepository.merchCheckoutState
    val merchPaymentVerifyState: StateFlow<NetworkResult<PaymentVerificationResponse>> get() = paymentRepository.merchPaymentVerifyState

    private var _bottomSheetState = MutableStateFlow<PaymentState>(PaymentState.Idle)
    val bottomSheetState: StateFlow<PaymentState> get() = _bottomSheetState

    private var _paymentBottomSheetState = MutableStateFlow<PaymentState>(PaymentState.Idle)
    val paymentBottomSheetState: StateFlow<PaymentState> get() = _paymentBottomSheetState

    private var _isMerchPayment = MutableStateFlow<Boolean>(false)
    val isMerchPayment : StateFlow<Boolean> get () = _isMerchPayment



    val accessToken: Flow<String> = prefs.getAccessToken()
    val zealId: Flow<String> = prefs.getZealId()

    fun updateBottomSheetState(state: PaymentState) {
        _bottomSheetState.value = state
    }

    fun updatePaymentBottomSheetState(state: PaymentState){
        _paymentBottomSheetState.value = state
    }

    fun updateMerchPaymentState(state: Boolean){
        _isMerchPayment.value = state
    }

    fun merchPaymentVerify(
        paymentVerificationRequest: PaymentVerificationRequest
    ) {
        viewModelScope.launch {
            paymentRepository.merchPaymentVerify(accessToken.first(), paymentVerificationRequest)
        }
    }

    fun merchCheckout(
        merchCheckoutRequest: MerchCheckoutRequest
    ){
        viewModelScope.launch {
            paymentRepository.merchCheckout(accessToken.first(), merchCheckoutRequest)
        }
    }

    fun paymentVerify(
        paymentVerificationRequest: PaymentVerificationRequest
    ){
       viewModelScope.launch {
           paymentRepository.paymentVerify(accessToken.first(), paymentVerificationRequest)
       }
    }

    fun getZealId(){
        viewModelScope.launch {
            Log.d("zealId","${accessToken.first()}")
            paymentRepository.getZealId(accessToken.first())
        }
    }

    fun getZealId2(token: String){
        viewModelScope.launch {
            Log.d("zealId","${token}")
            paymentRepository.getZealId(token)
        }
    }

    fun checkout(accessToken: String){
        viewModelScope.launch {
            paymentRepository.checkout(accessToken)
        }
    }

    fun saveZealID(zealId: String){
        viewModelScope.launch {
            prefs.saveZealId(zealId)
        }
    }

    fun removePaymentVerifyState(){
        viewModelScope.launch {
            paymentRepository.removePaymentVerifyState()
        }
    }

    fun removeGetZealState(){
        viewModelScope.launch {
            paymentRepository.removeGetZealState()
        }
    }

    fun removeMerchCheckoutState(){
        viewModelScope.launch {
            paymentRepository.removeMerchCheckoutState()
        }
    }

    fun removeCheckoutState(){
        viewModelScope.launch {
            paymentRepository.removeCheckoutState()
        }
    }

    fun removeMerchPaymentVerifyState(){
        viewModelScope.launch {
            paymentRepository.removeMerchPaymentVerifyState()
        }
    }
}