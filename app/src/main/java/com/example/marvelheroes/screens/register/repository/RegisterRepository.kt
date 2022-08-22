package com.example.marvelheroes.screens.register.repository

import com.example.marvelheroes.repositories.database.AuthenticatorDatabase
import com.example.marvelheroes.screens.register.model.UserAccount
import com.example.marvelheroes.utils.Resource
import com.google.firebase.auth.AuthResult
import javax.inject.Inject
import javax.inject.Singleton

interface RepositoryRegister {
    suspend fun createUser(userAccount: UserAccount): Resource<AuthResult>
}

@Singleton
class RegisterRepository @Inject constructor(private val database: AuthenticatorDatabase) : RepositoryRegister {
    override suspend fun createUser(userAccount: UserAccount): Resource<AuthResult> {
        return database.createUser(userAccount)
    }
}
