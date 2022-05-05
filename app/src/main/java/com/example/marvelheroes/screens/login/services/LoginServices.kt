package com.example.marvelheroes.screens.login.services

import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.login.repository.LoginRepository
import com.example.marvelheroes.screens.login.repository.RepositoryLogin
import javax.inject.Inject

interface ServicesLogin {
   suspend fun loginUser(user: UserLogin): Boolean
}


class LoginServices : ServicesLogin {

    @Inject
    lateinit var loginRepository: RepositoryLogin


    override suspend fun loginUser(user: UserLogin): Boolean {
        loginRepository = LoginRepository()
        return loginRepository.loginUser(user)
    }
}