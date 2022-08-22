package com.example.marvelheroes.di.modules

import com.example.marvelheroes.repositories.database.AuthenticatorDatabase
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
    fun provideLoginRepository(database: AuthenticatorDatabase): RepositoryLogin {
        return LoginRepository(database)
    }

    @Singleton
    @Provides
    fun provideLoginService(repository: RepositoryLogin): ServicesLogin {
        return LoginServices(repository)
    }

    @Singleton
    @Provides
    fun provideViewModel(services: ServicesLogin): ViewModelLogin {
        return LoginViewModel(services)
    }
}
