package com.gdg.zealicon2k25.domain.repository

import android.util.Log
import com.gdg.zealicon2k25.data.models.CheckoutResponse
import com.gdg.zealicon2k25.data.models.GetZealResponse
import com.gdg.zealicon2k25.data.models.MerchCheckoutRequest
import com.gdg.zealicon2k25.data.models.MerchCheckoutResponse
import com.gdg.zealicon2k25.data.models.PaymentVerificationRequest
import com.gdg.zealicon2k25.data.models.PaymentVerificationResponse
import com.gdg.zealicon2k25.data.remote.PaymentAPI
import com.gdg.zealicon2k25.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import java.net.SocketTimeoutException
import javax.inject.Inject

class PaymentRepository @Inject constructor(private val paymentAPI: PaymentAPI) {

    private var _checkoutState =
        MutableStateFlow<NetworkResult<CheckoutResponse>>(NetworkResult.Start())
    val checkoutState: StateFlow<NetworkResult<CheckoutResponse>> get() = _checkoutState

    private var _paymentVerifyState =
        MutableStateFlow<NetworkResult<PaymentVerificationResponse>>(NetworkResult.Start())
    val paymentVerifyState: StateFlow<NetworkResult<PaymentVerificationResponse>> get() = _paymentVerifyState

    private var _getZealIdState =
        MutableStateFlow<NetworkResult<GetZealResponse>>(NetworkResult.Start())
    val getZealIdState: StateFlow<NetworkResult<GetZealResponse>> get() = _getZealIdState

    private var _merchCheckoutState =
        MutableStateFlow<NetworkResult<MerchCheckoutResponse>>(NetworkResult.Start())
    val merchCheckoutState: StateFlow<NetworkResult<MerchCheckoutResponse>> get() = _merchCheckoutState

    private var _merchPaymentVerifyState =
        MutableStateFlow<NetworkResult<PaymentVerificationResponse>>(NetworkResult.Start())
    val merchPaymentVerifyState: StateFlow<NetworkResult<PaymentVerificationResponse>> get() = _merchPaymentVerifyState

    suspend fun merchPaymentVerify(
        accessToken: String,
        paymentVerificationRequest: PaymentVerificationRequest
    ) {
        _merchPaymentVerifyState.value = NetworkResult.Loading()
        try {
            val response = paymentAPI.paymentVerify(accessToken, paymentVerificationRequest)
            Log.d("message123", response.body().toString())
            Log.d("message123*", response.toString())
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _merchPaymentVerifyState.value = NetworkResult.Success(responseBody)
                } else {
                    _merchPaymentVerifyState.value = NetworkResult.Error("Response body is null")
                }
            } else if (response.errorBody() != null) {
                Log.d("message12", response.errorBody().toString())
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _merchPaymentVerifyState.value = NetworkResult.Error(errObj.getString("message"))
            } else {
                _merchPaymentVerifyState.value = NetworkResult.Error("Something went wrong")
            }
        } catch (e: SocketTimeoutException) {
            Log.d("message1", e.toString())
            _merchPaymentVerifyState.value = NetworkResult.Error("Please try again!")
        } catch (e: Exception) {
            Log.d("message12*", e.toString())
            _merchPaymentVerifyState.value = NetworkResult.Error("Unexpected error occurred")
        }

    }

    suspend fun merchCheckout(accessToken: String, merchCheckoutRequest: MerchCheckoutRequest){
        _merchCheckoutState.value = NetworkResult.Loading()
        try{
            val response = paymentAPI.merchCheckout(accessToken = accessToken, merchCheckoutRequest = merchCheckoutRequest)
            Log.d("message123", response.body().toString())
            Log.d("message123*", response.toString())
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _merchCheckoutState.value = NetworkResult.Success(responseBody)
                } else {
                    _merchCheckoutState.value = NetworkResult.Error("Response body is null")
                }
            } else if (response.errorBody() != null) {
                Log.d("message12", response.errorBody().toString())
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _merchCheckoutState.value = NetworkResult.Error(errObj.getString("message"))
            } else {
                _merchCheckoutState.value = NetworkResult.Error("Something went wrong")
            }
        }catch (e: SocketTimeoutException) {
            Log.d("message1", e.toString())
            _merchCheckoutState.value = NetworkResult.Error("Please try again!")
        } catch (e: Exception) {
            Log.d("message12*", e.toString())
            _merchCheckoutState.value = NetworkResult.Error("Unexpected error occurred")
        }
    }


    suspend fun getZealId(accessToken: String) {
        _getZealIdState.value = NetworkResult.Loading()
        try{
            val response = paymentAPI.getZealId(accessToken)
            Log.d("message123", response.body().toString())
            Log.d("message123*", response.toString())
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _getZealIdState.value = NetworkResult.Success(responseBody)
                } else {
                    _getZealIdState.value = NetworkResult.Error("Response body is null")
                }
            } else if (response.errorBody() != null) {
                Log.d("message12", response.errorBody().toString())
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _getZealIdState.value = NetworkResult.Error(errObj.getString("message"))
            } else {
                _getZealIdState.value = NetworkResult.Error("Something went wrong")
            }
        }catch (e: SocketTimeoutException) {
            Log.d("message1", e.toString())
            _getZealIdState.value = NetworkResult.Error("Please try again!")
        } catch (e: Exception) {
            Log.d("message12*", e.toString())
            _getZealIdState.value = NetworkResult.Error("Unexpected error occurred")
        }
    }

    suspend fun paymentVerify(
        accessToken: String,
        paymentVerificationRequest: PaymentVerificationRequest
    ) {
        _paymentVerifyState.value = NetworkResult.Loading()
        try {
            val response = paymentAPI.paymentVerify(accessToken, paymentVerificationRequest)
            Log.d("message123", response.body().toString())
            Log.d("message123*", response.toString())
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _paymentVerifyState.value = NetworkResult.Success(responseBody)
                } else {
                    _paymentVerifyState.value = NetworkResult.Error("Response body is null")
                }
            } else if (response.errorBody() != null) {
                Log.d("message12", response.errorBody().toString())
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _paymentVerifyState.value = NetworkResult.Error(errObj.getString("message"))
            } else {
                _paymentVerifyState.value = NetworkResult.Error("Something went wrong")
            }
        } catch (e: SocketTimeoutException) {
            Log.d("message1", e.toString())
            _paymentVerifyState.value = NetworkResult.Error("Please try again!")
        } catch (e: Exception) {
            Log.d("message12*", e.toString())
            _paymentVerifyState.value = NetworkResult.Error("Unexpected error occurred")
        }

    }

    suspend fun checkout(accessToken: String) {
        _checkoutState.value = NetworkResult.Loading()
        try {
            val response = paymentAPI.checkout(accessToken)
            Log.d("message123", response.body().toString())
            Log.d("message123*", response.toString())
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _checkoutState.value = NetworkResult.Success(responseBody)
                } else {
                    _checkoutState.value = NetworkResult.Error("Response body is null")
                }
            } else if (response.errorBody() != null) {
                Log.d("message12", response.errorBody().toString())
                val errObj = JSONObject(response.errorBody()!!.charStream().readText())
                _checkoutState.value = NetworkResult.Error(errObj.getString("message"))
            } else {
                _checkoutState.value = NetworkResult.Error("Something went wrong")
            }
        } catch (e: SocketTimeoutException) {
            Log.d("message1", e.toString())
            _checkoutState.value = NetworkResult.Error("Please try again!")
        } catch (e: Exception) {
            Log.d("message12*", e.toString())
            _checkoutState.value = NetworkResult.Error("Unexpected error occurred")
        }
    }

    fun removePaymentVerifyState(){
        _paymentVerifyState.value = NetworkResult.Start()
    }

    fun removeGetZealState(){
        _getZealIdState.value = NetworkResult.Start()
    }

    fun removeMerchCheckoutState(){
        _merchCheckoutState.value = NetworkResult.Start()
    }

    fun removeCheckoutState(){
        _checkoutState.value = NetworkResult.Start()
    }

    fun removeMerchPaymentVerifyState(){
        _merchPaymentVerifyState.value = NetworkResult.Start()
    }
}