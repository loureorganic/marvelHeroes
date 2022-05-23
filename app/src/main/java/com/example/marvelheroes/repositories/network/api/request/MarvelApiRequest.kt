package com.example.marvelheroes.repositories.network.api.request

import com.example.marvelheroes.repositories.network.api.models.MarvelApi
import com.example.marvelheroes.repositories.network.api.utils.ApiConstants.PUBLIC_KEY
import com.example.marvelheroes.repositories.network.api.utils.ApiConstants.md5
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.Instant
import java.time.format.DateTimeFormatter


interface MarvelApiRequest {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") timestamp: String = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("hash") hash: String = md5,
        @Query("limit") count : Int = 50,
        @Query("offset") offset : Int = 0
    ): MarvelApi
}