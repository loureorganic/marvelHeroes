package com.example.marvelheroes.screens.home.repository

import com.example.marvelheroes.repositories.network.api.models.characterModel.MarvelApi
import com.example.marvelheroes.repositories.network.api.models.comicsModel.ComicsModel
import com.example.marvelheroes.repositories.network.api.models.seriesModel.SeriesModel
import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import com.example.marvelheroes.repositories.network.api.utils.ApiConstants.convertToMd5
import com.example.marvelheroes.repositories.network.api.utils.ApiConstants.generateTimestamp
import com.example.marvelheroes.repositories.network.api.utils.ApiConstants.hash
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

interface RepositoryHome {
    suspend fun getCharactersForSliding(): Flow<MarvelApi>
    suspend fun getAllCharacters(): Flow<MarvelApi>
    suspend fun getAnCharacter(characterId: String): Flow<MarvelApi>
    suspend fun getAllComics(): Flow<ComicsModel>
    suspend fun getAllSeries(): Flow<SeriesModel>
}

@Singleton
class HomeRepository @Inject constructor(private val retrofit: RetrofitInstance): RepositoryHome {

    override suspend fun getCharactersForSliding(): Flow<MarvelApi> = flow {
        val timestamp = generateTimestamp()
        emit(retrofit.api.getCharactersForSliding(hash = convertToMd5(hash(timestamp = timestamp)), timestamp = timestamp))
    }

    override suspend fun getAllCharacters(): Flow<MarvelApi> = flow {
        val timestamp = generateTimestamp()
         emit(retrofit.api.getAllCharacters(hash = convertToMd5(hash(timestamp = timestamp)), timestamp = timestamp))
    }

    override suspend fun getAnCharacter(characterId: String) : Flow<MarvelApi> = flow{
        val timestamp = generateTimestamp()
        emit(retrofit.api.getAnCharacters(characterId, hash = convertToMd5(hash(timestamp = timestamp)), timestamp = timestamp))
    }

    override suspend fun getAllComics() : Flow<ComicsModel> = flow {
        val timestamp = generateTimestamp()
        emit(retrofit.api.getAllComics(hash = convertToMd5(hash(timestamp = timestamp)), timestamp = timestamp))
    }

    override suspend fun getAllSeries(): Flow<SeriesModel> = flow {
        val timestamp = generateTimestamp()
        emit(retrofit.api.getAllSeries(hash = convertToMd5(hash(timestamp = timestamp)), timestamp = timestamp))
    }

}