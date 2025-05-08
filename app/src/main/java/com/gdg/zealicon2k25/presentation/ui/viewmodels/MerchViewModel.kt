package com.gdg.zealicon2k25.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.zealicon2k25.data.models.Merch
import com.gdg.zealicon2k25.data.models.MerchResponse
import com.gdg.zealicon2k25.domain.repository.MerchRepository
import com.gdg.zealicon2k25.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MerchViewModel @Inject constructor(
    private val merchRepository: MerchRepository,
) : ViewModel() {
    val merchState: StateFlow<NetworkResult<MerchResponse>>
        get() = merchRepository.merchState

    private var _selectedMerch: Merch? = null
    val selectedMerch: Merch? get() = _selectedMerch


    fun setMerch(merch:Merch){
        _selectedMerch = merch
    }


    fun getMerch(token: String){
        viewModelScope.launch {
            merchRepository.getMerch(token)
        }
    }
}