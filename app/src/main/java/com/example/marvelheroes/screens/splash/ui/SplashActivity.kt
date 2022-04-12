package com.example.marvelheroes.screens.splash.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelheroes.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}