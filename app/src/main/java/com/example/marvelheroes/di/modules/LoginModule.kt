package com.example.marvelheroes.di.modules

import com.example.marvelheroes.screens.login.repository.LoginRepository
import com.example.marvelheroes.screens.login.repository.RepositoryLogin
import com.example.marvelheroes.screens.login.services.LoginServices
import com.example.marvelheroes.screens.login.services.ServicesLogin
import com.example.marvelheroes.screens.login.viewmodel.LoginViewModel
import com.example.marvelheroes.screens.login.viewmodel.ViewModelLogin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideLoginRepository(): RepositoryLogin {
        return LoginRepository()
    }

    @Singleton
    @Provides
    fun provideLoginService(): ServicesLogin {
        return LoginServices()
    }

    @Singleton
    @Provides
    fun provideViewModel(): ViewModelLogin {
        return LoginViewModel()
    }
}