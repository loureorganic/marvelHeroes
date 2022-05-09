package com.example.marvelheroes.screens.login.services

import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.login.repository.RepositoryLogin
import javax.inject.Inject
import javax.inject.Singleton

interface ServicesLogin {
    suspend fun loginUser(user: UserLogin): Boolean
}

@Singleton
class LoginServices @Inject constructor(private val loginRepository: RepositoryLogin) : ServicesLogin {

    override suspend fun loginUser(user: UserLogin): Boolean {
        return loginRepository.loginUser(user)
    }
}