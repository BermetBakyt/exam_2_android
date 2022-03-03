package com.ber.exam2android

import android.location.Location

class Response {
    data class RepoResult(
        val items: List<Character>
    )

    data class Character (
        val character_id: Long?,
        val name: String,
        val status: String,
        val species: String,
        val gender: String,
        val origin: Location,
        val location: String?,
        val date_created: String,
        val image: String,
        val episode: List<String>
        )
}