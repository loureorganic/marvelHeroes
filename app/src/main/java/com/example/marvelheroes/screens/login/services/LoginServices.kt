package com.example.marvelheroes.screens.login.services

import androidx.core.util.PatternsCompat
import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.login.repository.RepositoryLogin
import com.example.marvelheroes.screens.login.utils.LoginConstants
import javax.inject.Inject
import javax.inject.Singleton

interface ServicesLogin {
    suspend fun loginUser(user: UserLogin): Boolean
    fun dataLoginValidation(user: UserLogin) : String
}

@Singleton
class LoginServices @Inject constructor(private val loginRepository: RepositoryLogin) : ServicesLogin {

    override suspend fun loginUser(user: UserLogin): Boolean {
        return loginRepository.loginUser(user)
    }

    override fun dataLoginValidation(user: UserLogin): String {
        return  if (user.password.isEmpty() && user.password.length <= 6) {
            LoginConstants.EMPTY_PASSWORD
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(user.email).matches()) {
            LoginConstants.INVALID_EMAIL
        }  else {
            LoginConstants.VALID
        }
    }
}