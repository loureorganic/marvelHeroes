package com.example.marvelheroes.di.modules

import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import com.example.marvelheroes.screens.character.repository.CharacterRepository
import com.example.marvelheroes.screens.character.repository.RepositoryCharacter
import com.example.marvelheroes.screens.character.services.CharacterServices
import com.example.marvelheroes.screens.character.services.ServicesCharacter
import com.example.marvelheroes.screens.character.viewmodel.CharacterViewModel
import com.example.marvelheroes.screens.character.viewmodel.ViewModelCharacter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun providesCharacterViewModel(services: ServicesCharacter): ViewModelCharacter {
        return CharacterViewModel(services)
    }

    @Provides
    @Singleton
    fun providesCharacterServices(repository: RepositoryCharacter): ServicesCharacter {
        return CharacterServices(repository)
    }

    @Provides
    @Singleton
    fun providesCharacterRepository(retrofitInstance: RetrofitInstance): RepositoryCharacter {
        return CharacterRepository(retrofitInstance)
    }
}
