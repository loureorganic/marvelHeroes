package com.example.marvelheroes.screens.character.repository

import com.example.marvelheroes.model.comics.ComicsModel
import com.example.marvelheroes.model.series.SeriesModel
import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import com.example.marvelheroes.repositories.network.api.utils.ApiConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

interface RepositoryCharacter {
    suspend fun getCharacterSeries(seriesId: String): Flow<SeriesModel>
    suspend fun getCharacterComics(comicsId: String): Flow<ComicsModel>
}

@Singleton
class CharacterRepository @Inject constructor(private val retrofit: RetrofitInstance) : RepositoryCharacter {

    override suspend fun getCharacterSeries(seriesId: String): Flow<SeriesModel> = flow {
        val timestamp = ApiConstants.generateTimestamp()
        emit(retrofit.api.getCharacterSeries(hash = ApiConstants.convertToMd5(ApiConstants.hash(timestamp = timestamp)), timestamp = timestamp, id = seriesId))
    }

    override suspend fun getCharacterComics(comicsId: String): Flow<ComicsModel> = flow {
        val timestamp = ApiConstants.generateTimestamp()
        emit(retrofit.api.getCharacterComics(hash = ApiConstants.convertToMd5(ApiConstants.hash(timestamp = timestamp)), timestamp = timestamp, id = comicsId))
    }
}
