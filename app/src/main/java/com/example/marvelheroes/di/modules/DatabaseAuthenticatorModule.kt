package com.example.marvelheroes.di.modules

import com.example.marvelheroes.repositories.database.AuthenticatorDatabase
import com.example.marvelheroes.repositories.database.DatabaseAuthenticator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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
    fun provideFirebaseDatabaseInstance(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Singleton
    @Provides
    fun provideDatabaseAuthenticatorInstance(
        firebaseAuth: FirebaseAuth,
        firebaseDatabase: FirebaseDatabase
    ): AuthenticatorDatabase {
        return DatabaseAuthenticator(firebaseAuth, firebaseDatabase)
    }
}