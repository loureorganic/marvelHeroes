package com.example.marvelheroes.di.modules

import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import com.example.marvelheroes.screens.home.repository.HomeRepository
import com.example.marvelheroes.screens.home.repository.RepositoryHome
import com.example.marvelheroes.screens.home.services.HomeServices
import com.example.marvelheroes.screens.home.services.ServicesHome
import com.example.marvelheroes.screens.home.viewmodel.HomeViewModel
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeViewModel(services: ServicesHome): ViewModelHome {
        return HomeViewModel(services)
    }

    @Provides
    @Singleton
    fun provideHomeServices(repository: RepositoryHome) : ServicesHome {
        return HomeServices(repository)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(retrofitInstance: RetrofitInstance) : RepositoryHome {
        return HomeRepository(retrofitInstance)
    }

}
