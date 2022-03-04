package com.ber.exam2android

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {
    @GET("character")
    fun getRepositories(): Observable<Response>

    @GET("character/{id}")
    fun getCharacterById(@Path("id")id: Long): Single<Character>
}