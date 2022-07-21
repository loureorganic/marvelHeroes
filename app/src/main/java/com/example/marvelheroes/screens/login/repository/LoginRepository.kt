package com.example.marvelheroes.screens.login.repository

import com.example.marvelheroes.repositories.database.AuthenticatorDatabase
import com.example.marvelheroes.screens.login.model.UserLogin
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface RepositoryLogin {
    suspend fun loginUser(userLogin: UserLogin): Boolean
}

@Singleton
class LoginRepository @Inject constructor(private val database: AuthenticatorDatabase) : RepositoryLogin {
    override suspend fun loginUser(userLogin: UserLogin): Boolean {
        return database.loginUser(user = userLogin)
    }
}