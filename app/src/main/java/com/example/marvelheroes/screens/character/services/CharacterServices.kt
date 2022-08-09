package com.example.marvelheroes.screens.character.services

import com.example.marvelheroes.model.comics.ComicsModel
import com.example.marvelheroes.model.series.SeriesModel
import com.example.marvelheroes.screens.character.repository.RepositoryCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface ServicesCharacter {
    suspend fun getCharacterSeries(resourceURI: String): Flow<SeriesModel>
    suspend fun  getCharacterComics (resourceURI: String) : Flow<ComicsModel>
}

@Singleton
class CharacterServices @Inject constructor(private val repository: RepositoryCharacter) : ServicesCharacter {

    override suspend fun getCharacterSeries(resourceURI: String): Flow<SeriesModel> {
        val storyId = resourceURI.substring(43)
        return repository.getCharacterSeries(storyId)
    }

    override suspend fun getCharacterComics(resourceURI: String): Flow<ComicsModel> {
        val comicsId = resourceURI.substring(43)
        return repository.getCharacterComics(comicsId)
    }


}