package com.example.marvelheroes.di.modules

import com.example.marvelheroes.repositories.network.api.request.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit() : RetrofitInstance{
        return RetrofitInstance()
    }

}