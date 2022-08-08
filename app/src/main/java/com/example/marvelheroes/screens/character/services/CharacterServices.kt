package com.example.marvelheroes.screens.character.services

import com.example.marvelheroes.repositories.network.api.models.characterModel.MarvelApi
import com.example.marvelheroes.screens.character.model.series.seriesOfCharacter
import com.example.marvelheroes.screens.character.repository.RepositoryCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface ServicesCharacter {
    suspend fun getCharacterSeries(resourceURI: String): Flow<seriesOfCharacter>
}

@Singleton
class CharacterServices @Inject constructor(private val repository: RepositoryCharacter) : ServicesCharacter {

    override suspend fun getCharacterSeries(resourceURI: String): Flow<seriesOfCharacter> {
        val storyId = resourceURI.substring(43)
        return repository.getCharacterSeries(storyId)
    }
}