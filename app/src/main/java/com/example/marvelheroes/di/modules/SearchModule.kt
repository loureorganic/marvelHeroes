package com.example.marvelheroes.di.modules

import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import com.example.marvelheroes.screens.search.repository.RepositorySearch
import com.example.marvelheroes.screens.search.repository.SearchRepository
import com.example.marvelheroes.screens.search.services.SearchServices
import com.example.marvelheroes.screens.search.services.ServicesSearch
import com.example.marvelheroes.screens.search.viewmodel.SearchViewModel
import com.example.marvelheroes.screens.search.viewmodel.ViewModelSearch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchViewModel(services: ServicesSearch): ViewModelSearch{
        return SearchViewModel(services)
    }

    @Provides
    @Singleton
    fun provideSearchServices(repository: RepositorySearch) : ServicesSearch{
        return SearchServices(repository)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(retrofitInstance: RetrofitInstance): RepositorySearch{
        return SearchRepository(retrofitInstance)
    }
}