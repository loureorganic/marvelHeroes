package com.example.marvelheroes.screens.register.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelheroes.databinding.ActivityRegisterBinding
import com.example.marvelheroes.screens.home.ui.HomeActivity
import com.example.marvelheroes.screens.register.model.UserAccount
import com.example.marvelheroes.screens.register.viewmodel.ViewModelRegister
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    @Inject
    lateinit var viewModel: ViewModelRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        binding.button2.setOnClickListener{
            val user = UserAccount(
                fullName=  binding.textInputEditText4.text.toString().trim(),
                email = binding.textInputEditText5.text.toString().trim(),
                password = binding.textInputEditText6.text.toString().trim(),
                confirmPassword = binding.textInputEditText7.text.toString().trim(),

                )
            //ValidateData
        }

        viewModel.createUser(UserAccount(email = "tail@gmail.com", password = "12345678", confirmPassword = "12345678", fullName = "TESTE"))

        viewModel.booleanCreateUserLiveData.observe(this){
            if(it){
            startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
        }}
    }
}