package com.example.marvelheroes.screens.home.repository

import com.example.marvelheroes.repositories.network.api.models.MarvelApi
import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

interface RepositoryHome{
    suspend fun getCharacters() : Result<Response<MarvelApi>>
}


@AndroidEntryPoint
class HomeRepository : RepositoryHome{

    override suspend fun getCharacters(): Result<Response<MarvelApi>> {
        val retrofit =  RetrofitInstance()
        return retrofit.api.getCharacters()
    }
}