package com.example.marvelheroes.repositories.database

import com.example.marvelheroes.screens.login.model.UserLogin
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface AuthenticatorDatabase {
    suspend fun loginUser(user: UserLogin): Boolean
}

@Singleton
class DatabaseAuthenticator @Inject constructor(private val firebaseAuth: FirebaseAuth) : AuthenticatorDatabase {
    override suspend fun loginUser(user: UserLogin): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(user.email, user.password).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}