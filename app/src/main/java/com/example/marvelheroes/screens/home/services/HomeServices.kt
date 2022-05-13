package com.example.marvelheroes.screens.home.services

import com.example.marvelheroes.repositories.network.api.models.MarvelApi
import com.example.marvelheroes.screens.home.repository.RepositoryHome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface ServicesHome {
    suspend fun getCharacters() : Flow<MarvelApi>
}

@Singleton
class HomeServices @Inject constructor(private val repository: RepositoryHome) : ServicesHome {
    override suspend fun getCharacters() : Flow<MarvelApi> {
        return repository.getCharacters()
    }
}