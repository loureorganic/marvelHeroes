package com.example.marvelheroes.repositories.database


import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.register.model.UserAccount
import com.example.marvelheroes.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface AuthenticatorDatabase {
    suspend fun loginUser(user: UserLogin): Boolean
    suspend fun createUser(user: UserAccount): Resource<AuthResult>
}

@Singleton
class DatabaseAuthenticator @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : AuthenticatorDatabase {
    override suspend fun loginUser(user: UserLogin): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(user.email, user.password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun createUser(user: UserAccount): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {

            val result = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()


            val uid = firebaseAuth.uid

            if (uid != null) {
                firebaseDatabase.getReference("Users").child(uid).setValue(user).await()
            }
            Resource.Success(result)
        }
    }
}