package com.ber.exam2android

import android.location.Location

data class Response (val results: List<Character>
)

data class Character (
    val character_id: Long,
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val date_created: String?,
    val image: String?
    )
