package com.example.marvelheroes.di.modules

import com.example.marvelheroes.repositories.database.AuthenticatorDatabase
import com.example.marvelheroes.repositories.database.DatabaseAuthenticator
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseAuthenticatorModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthenticationInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideDatabaseAuthenticatorInstance(firebaseAuth: FirebaseAuth): AuthenticatorDatabase{
       return DatabaseAuthenticator(firebaseAuth)
    }
}