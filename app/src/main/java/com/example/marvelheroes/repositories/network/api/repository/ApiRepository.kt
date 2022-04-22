package com.example.marvelheroes.repositories.network.api.repository

import com.example.marvelheroes.repositories.network.api.models.MarvelApi
import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import io.reactivex.Observable
import retrofit2.Response

class ApiRepository {

    suspend fun getCharacters(): Observable<Response<MarvelApi>> {
       val retrofit =  RetrofitInstance()
        return retrofit.api.getCharacters()
    }
}