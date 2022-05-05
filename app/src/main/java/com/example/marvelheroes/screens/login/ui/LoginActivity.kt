package com.example.marvelheroes.screens.login.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.marvelheroes.databinding.ActivityLoginBinding
import com.example.marvelheroes.repositories.network.api.request.HashApi
import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.login.repository.LoginRepository
import com.example.marvelheroes.screens.login.repository.RepositoryLogin
import com.example.marvelheroes.screens.login.viewmodel.LoginViewModel
import com.example.marvelheroes.screens.login.viewmodel.ViewModelLogin
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var md5: HashApi

    private val repository: RepositoryLogin = LoginRepository()

    @Inject
    lateinit var viewModel : ViewModelLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()

        val userLogin = UserLogin(email = "kaiqueguimaraes@gmail.com", password = "1234567")

        lifecycleScope.launch {
           val  a =  repository.loginUser(userLogin)
            Log.i("HERE", "RECEBA $a" )

        }

        viewModel.loginUser(userLogin)
        viewModel.booleanLoginUserLiveData.observe(this){ response ->
            Log.i("LIVEDATA", "SYSTEM WORKING $response")
        }

    }
}