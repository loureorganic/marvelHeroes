package com.example.marvelheroes.screens.login.repository

import android.util.Log
import com.example.marvelheroes.screens.login.model.UserLogin
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

interface RepositoryLogin {
    suspend fun loginUser(userLogin: UserLogin): Boolean
}

class LoginRepository : RepositoryLogin {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun loginUser(userLogin: UserLogin): Boolean {
        return try {
            val data = firebaseAuth.signInWithEmailAndPassword(userLogin.email, userLogin.password).await()
            Log.i("RESULTADO", "SAIDA $data")
            true
        } catch (e: Exception) {
            false
        }
    }
}