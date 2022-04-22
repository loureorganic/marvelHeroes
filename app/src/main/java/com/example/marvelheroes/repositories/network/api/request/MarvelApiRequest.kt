package com.example.marvelheroes.repositories.network.api.request

import com.example.marvelheroes.repositories.network.api.models.MarvelApi
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET


interface MarvelApiRequest {
    @GET("/v1/public/characters?limit=9&ts=2022-04-22T04:43:38.308Z&apikey=1d90d4e7237d341041c5451496af7e3f&hash=350f2db9b2e121a72e9b22ca70dcf300")
    suspend fun getCharacters() : Observable<Response<MarvelApi>>
}