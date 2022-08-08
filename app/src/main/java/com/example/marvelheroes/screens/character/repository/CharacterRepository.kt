package com.example.marvelheroes.screens.character.repository

import com.example.marvelheroes.repositories.network.api.models.characterModel.MarvelApi
import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import com.example.marvelheroes.repositories.network.api.utils.ApiConstants
import com.example.marvelheroes.screens.character.model.series.seriesOfCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

interface RepositoryCharacter{
    suspend fun getCharacterSeries(seriesId: String) : Flow<seriesOfCharacter>
}

@Singleton
class CharacterRepository @Inject constructor(private val retrofit: RetrofitInstance): RepositoryCharacter {

    override suspend fun getCharacterSeries(seriesId: String): Flow<seriesOfCharacter> = flow {
        val timestamp = ApiConstants.generateTimestamp()
        emit(retrofit.api.getCharacterSeries(hash = ApiConstants.convertToMd5(ApiConstants.hash(timestamp = timestamp)), timestamp = timestamp, id = seriesId))
    }
}