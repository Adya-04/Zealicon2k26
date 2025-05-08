package com.gdg.zealicon2k25.data.models

data class EventsResponse(
    val events: List<Event>,
    val message: String,
    val success: Boolean
)