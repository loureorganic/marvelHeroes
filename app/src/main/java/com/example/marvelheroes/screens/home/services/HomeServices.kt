package com.example.marvelheroes.screens.home.services

import com.example.marvelheroes.repositories.network.api.models.characterModel.MarvelApi
import com.example.marvelheroes.repositories.network.api.models.comicsModel.ComicsModel
import com.example.marvelheroes.screens.home.repository.RepositoryHome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject
import javax.inject.Singleton

interface ServicesHome {
    suspend fun getCharactersForSliding(): Flow<MarvelApi>
    suspend fun getAllCharacters(): Flow<MarvelApi>
    suspend fun getAllComics(): Flow<ComicsModel>
}

@Singleton
class HomeServices @Inject constructor(private val repository: RepositoryHome) : ServicesHome {
    override suspend fun getCharactersForSliding(): Flow<MarvelApi> {
        return repository.getCharactersForSliding()
    }

    override suspend fun getAllCharacters(): Flow<MarvelApi> {
        return repository.getAllCharacters()
    }

    override suspend fun getAllComics(): Flow<ComicsModel> {
        return repository.getAllComics()
    }


}