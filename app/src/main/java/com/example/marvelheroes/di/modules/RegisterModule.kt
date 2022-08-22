package com.example.marvelheroes.di.modules

import com.example.marvelheroes.repositories.database.AuthenticatorDatabase
import com.example.marvelheroes.screens.register.repository.RegisterRepository
import com.example.marvelheroes.screens.register.repository.RepositoryRegister
import com.example.marvelheroes.screens.register.services.RegisterServices
import com.example.marvelheroes.screens.register.services.ServiceRegister
import com.example.marvelheroes.screens.register.viewmodel.RegisterViewModel
import com.example.marvelheroes.screens.register.viewmodel.ViewModelRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {

    @Singleton
    @Provides
    fun provideRegisterViewModel(services: ServiceRegister): ViewModelRegister {
        return RegisterViewModel(services)
    }

    @Singleton
    @Provides
    fun provideRegisterServices(repository: RegisterRepository): ServiceRegister {
        return RegisterServices(repository)
    }

    @Singleton
    @Provides
    fun provideRegisterRepository(database: AuthenticatorDatabase): RepositoryRegister {
        return RegisterRepository(database)
    }
}
