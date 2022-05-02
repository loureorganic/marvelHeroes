package com.example.marvelheroes.di.modules

import com.example.marvelheroes.screens.login.repository.LoginRepository
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
    fun provideLoginRepository() : LoginRepository{
        return LoginRepository()
    }
}