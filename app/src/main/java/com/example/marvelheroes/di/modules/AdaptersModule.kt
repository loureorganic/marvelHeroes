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
object AdaptersModule {

    @Provides
    @Singleton
    fun providePhotoAdapterInstance(context: Context, glide: ImageLoader ) : PhotoAdapter {
        return PhotoAdapter(context, glide)
    }
}