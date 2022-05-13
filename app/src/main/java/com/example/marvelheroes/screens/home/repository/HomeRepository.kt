package com.example.marvelheroes.screens.home.repository

import com.example.marvelheroes.repositories.network.api.models.MarvelApi
import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

interface RepositoryHome {
    suspend fun getCharacters(): Flow<MarvelApi>
}

@Singleton
class HomeRepository : RepositoryHome {

    override suspend fun getCharacters(): Flow<MarvelApi> = flow {
        val retrofit = RetrofitInstance()
        emit(retrofit.api.getCharacters())
    }
}