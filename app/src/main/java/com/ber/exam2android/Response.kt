package com.ber.exam2android

class Response {
    data class RepoResult(
        val items: List<Character>
    )

    data class Character (
        val character_id: Long?,
        val name: String?,
        val status: Long?,
        val species: String,
        val gender: String,
        val origin: Long?,
        val location: String?,
        val date_created: Long?
    )
}