package com.example.marvelheroes.screens.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelheroes.databinding.ActivityHomeBinding
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelHome: ViewModelHome

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        viewModelHome.getCharacters()
        viewModelHome.marvelList.observe(this){

        }
    }
}