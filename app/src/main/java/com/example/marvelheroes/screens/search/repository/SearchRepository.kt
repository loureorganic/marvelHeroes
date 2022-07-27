package com.example.marvelheroes.screens.search.repository

import com.example.marvelheroes.repositories.network.api.models.characterModel.MarvelApi
import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import com.example.marvelheroes.repositories.network.api.utils.ApiConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


interface RepositorySearch {
    suspend fun searchCharacter(nameSearched: String): Flow<MarvelApi>
}

@Singleton
class SearchRepository @Inject constructor(private val retrofit: RetrofitInstance) :
    RepositorySearch {
    override suspend fun searchCharacter(nameSearched: String): Flow<MarvelApi> = flow {
        val timestamp = ApiConstants.generateTimestamp()
        emit(
            retrofit.api.searchCharacter(
                hash = ApiConstants.convertToMd5(
                    ApiConstants.hash(
                        timestamp = timestamp
                    )
                ), timestamp = timestamp, name = nameSearched
            )
        )
    }
}