package com.example.marvelheroes.screens.register.services

import android.util.Log
import android.util.Patterns
import com.example.marvelheroes.screens.register.model.UserAccount
import com.example.marvelheroes.screens.register.repository.RepositoryRegister
import com.example.marvelheroes.screens.register.utils.RegisterConstants
import com.example.marvelheroes.utils.Resource
import com.google.firebase.auth.AuthResult
import javax.inject.Inject
import javax.inject.Singleton

interface ServiceRegister {
    suspend fun createUser(user: UserAccount): Resource<AuthResult>
    fun validateRegisterData(userAccount: UserAccount): String
}

@Singleton
class RegisterServices @Inject constructor(private val repository: RepositoryRegister) : ServiceRegister {
    override suspend fun createUser(user: UserAccount): Resource<AuthResult> {
        return repository.createUser(userAccount = user)
    }

    override fun validateRegisterData(userAccount: UserAccount): String {
        return if (userAccount.fullName.isEmpty()) {
            RegisterConstants.NAME_EMPTY
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userAccount.email).matches()) {
            RegisterConstants.EMAIL_INVALID
        } else if (userAccount.password.isEmpty()) {
            RegisterConstants.PASSWORD_EMPTY
        } else if (userAccount.confirmPassword.isEmpty()) {
            RegisterConstants.CONFIRM_PASSWORD_EMPTY
        } else if (userAccount.password != userAccount.confirmPassword) {
            RegisterConstants.PASSWORD_NOT_MATCH
        } else {
            RegisterConstants.VALID
        }
    }

}