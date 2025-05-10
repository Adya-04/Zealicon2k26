package com.gdg.zealicon2k25.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.zealicon2k25.data.models.EnrollEventReq
import com.gdg.zealicon2k25.data.models.EnrollEventResponse
import com.gdg.zealicon2k25.data.models.Event
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

    private var _selectedEvent: Event? = null
    val selectedEvent: Event? get() = _selectedEvent

    private var _eventList: List<Event> = emptyList()
    val eventList: List<Event> get() = _eventList

    fun setSelectedEvent(event: Event) {
        _selectedEvent = event
    }

    fun setEventList(events: List<Event>) {
        _eventList = events
    }

    fun getEvents(accessToken:String , eventType:String){
        viewModelScope.launch {
            eventsRepo.getEvents(accessToken , eventType)
        }
    }

    fun enrollEvent(token:String , enrollEventRequest: EnrollEventReq){
        viewModelScope.launch {
            eventsRepo.enrollEvent(token , enrollEventRequest)
        }
    }
}