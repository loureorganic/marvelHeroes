package com.example.marvelheroes.screens.login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelheroes.databinding.ActivityLoginBinding
import com.example.marvelheroes.screens.home.ui.HomeActivity
import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.login.utils.LoginConstants
import com.example.marvelheroes.screens.login.viewmodel.ViewModelLogin
import com.example.marvelheroes.screens.register.ui.RegisterActivity
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

        binding.textView6.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }

        binding.button.setOnClickListener {
            val userLogin = UserLogin(
                email = binding.textInputEditText.text.toString().trim(),
                password = binding.textInputEditText2.text.toString().trim()
            )
            dataLoginValidation(userLogin)
        }
    }

    private fun dataLoginValidation(userLogin: UserLogin) {

        when (viewModel.dataLoginValidation(userLogin)) {
            LoginConstants.INVALID_EMAIL -> {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
            }
            LoginConstants.EMPTY_PASSWORD -> {
                Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
            }
            LoginConstants.VALID -> {
                loginAccount(userLogin)
            }
        }
    }

    private fun loginAccount(userLogin: UserLogin) {
        viewModel.loginUser(userLogin)

        viewModel.booleanLoginUserLiveData.observe(this) { response ->
            if (response) {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
            else {
                Toast.makeText(this, "Email or password are invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}