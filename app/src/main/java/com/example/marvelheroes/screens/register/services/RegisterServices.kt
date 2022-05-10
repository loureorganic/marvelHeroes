package com.example.marvelheroes.screens.register.services

import com.example.marvelheroes.screens.register.model.UserAccount
import com.example.marvelheroes.screens.register.repository.RepositoryRegister
import com.example.marvelheroes.utils.Resource
import com.google.firebase.auth.AuthResult
import javax.inject.Inject
import javax.inject.Singleton

interface ServiceRegister{
 suspend fun createUser(user : UserAccount) : Resource<AuthResult>
}

@Singleton
class RegisterServices @Inject constructor(private val repository: RepositoryRegister): ServiceRegister {
    override suspend fun createUser(user : UserAccount): Resource<AuthResult> {
        return repository.createUser(userAccount = user)
    }

}