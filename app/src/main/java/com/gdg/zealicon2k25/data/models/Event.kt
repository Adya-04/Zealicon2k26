package com.gdg.zealicon2k25.data.models

data class Event(
    val _id: String,
    val contact_info: String,
    val description: String,
    val enrollment_end: String,
    val enrollment_start: String,
    val event_end: String,
    val event_start: String,
    val prize: Int,
    val society: String,
    val title: String,
    val type: String,
    val venue: String,
    val image: String
)