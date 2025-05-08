package com.gdg.zealicon2k25.data.models

data class GetZealResponse(
    val message: String,
    val success: Boolean,
    val userDetails: UserDetails2,
    val zeal_id: String
)

data class UserDetails2(
    val name: String,
    val id_card: String,
    val photo: String,
)