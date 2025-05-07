package com.gdg.zealicon2k25.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.zealicon2k25.data.models.EnrollEventResponse
import com.gdg.zealicon2k25.data.models.EventsResponse
import com.gdg.zealicon2k25.domain.repository.EventsRepository
import com.gdg.zealicon2k25.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val eventsRepo: EventsRepository): ViewModel(){

    val events: StateFlow<NetworkResult<EventsResponse>>
        get() = eventsRepo.events

    val enrollEvent : StateFlow<NetworkResult<EnrollEventResponse>>
        get()=eventsRepo.enrollEventState

    fun getEvents(accessToken:String , eventType:String){
        viewModelScope.launch {
            eventsRepo.getEvents(accessToken , eventType)
        }
    }

    fun enrollEvent(){
        viewModelScope.launch {
            eventsRepo.enrollEvent()
        }
    }
}