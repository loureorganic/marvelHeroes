package com.example.marvelheroes.screens.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelheroes.databinding.ActivityLoginBinding
import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.login.viewmodel.ViewModelLogin
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var  viewModel : ViewModelLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val userLogin = UserLogin(email = "kaiqueguimaraes@gmail.com", password = "1234567")

        viewModel.loginUser(userLogin)
        viewModel.booleanLoginUserLiveData.observe(this){ response ->
            //go to other page
        }

    }
}