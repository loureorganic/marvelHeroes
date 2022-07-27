package com.example.marvelheroes.screens.search.services

import com.example.marvelheroes.repositories.network.api.models.characterModel.MarvelApi
import com.example.marvelheroes.screens.search.repository.RepositorySearch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface ServicesSearch {
    suspend fun searchCharacter(nameSearched: String): Flow<MarvelApi>
}

@Singleton
class SearchServices @Inject constructor(private val repository: RepositorySearch) :
    ServicesSearch {

    override suspend fun searchCharacter(nameSearched: String): Flow<MarvelApi> {
        return repository.searchCharacter(nameSearched)
    }
}