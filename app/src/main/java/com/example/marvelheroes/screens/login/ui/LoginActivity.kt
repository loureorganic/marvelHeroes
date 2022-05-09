package com.example.marvelheroes.screens.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelheroes.databinding.ActivityLoginBinding
import com.example.marvelheroes.screens.home.ui.HomeActivity
import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.login.viewmodel.ViewModelLogin
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var viewModel: ViewModelLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.button.setOnClickListener {
            val userLogin = UserLogin(
                email = binding.textInputEditText.text.toString().trim(),
                password = binding.textInputEditText2.text.toString().trim()
            )
            viewModel.loginUser(userLogin)
        }

        viewModel.booleanLoginUserLiveData.observe(this) { response ->
            if (response) {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
        }

    }
}