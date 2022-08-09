package com.example.marvelheroes.screens.home.services

import com.example.marvelheroes.model.comics.ComicsModel
import com.example.marvelheroes.model.series.SeriesModel
import com.example.marvelheroes.repositories.network.api.models.characterModel.MarvelApi
import com.example.marvelheroes.screens.home.repository.RepositoryHome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface ServicesHome {
    suspend fun getCharactersForSliding(): Flow<MarvelApi>
    suspend fun getAllCharacters(): Flow<MarvelApi>
    suspend fun getAllComics(): Flow<ComicsModel>
    suspend fun getAllSeries(): Flow<SeriesModel>
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

    override suspend fun getAllSeries(): Flow<SeriesModel> {
        return repository.getAllSeries()
    }


}