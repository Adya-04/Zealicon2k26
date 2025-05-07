package com.gdg.zealicon2k25.data.models

data class SignupRequest(
    val id_card: IdCard,
    val name: String,
    val phone: Long,
    val photo: Photo
)

data class Photo(
    val public_id: String,
    val secure_url: String
)


data class IdCard(
    val public_id: String,
    val secure_url: String
)