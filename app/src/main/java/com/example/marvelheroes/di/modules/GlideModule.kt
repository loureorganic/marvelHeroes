package com.example.marvelheroes.di.modules

import android.content.Context
import com.example.marvelheroes.screens.home.adapters.ImageLoader
import com.example.marvelheroes.screens.home.adapters.PhotoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GlideModule {

    @Singleton
    @Provides
    fun provideGlideInstance() : ImageLoader {
        return ImageLoader()
    }

}