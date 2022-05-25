package com.example.marvelheroes.screens.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import com.example.marvelheroes.screens.home.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.home.ui.utils.AutoSliding
import com.example.marvelheroes.screens.home.ui.utils.PhotoAdapter
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelHome: ViewModelHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelHome.getCharacters()
        viewModelHome.marvelList.observe(this) { list ->
            setContent {
               MarvelHeroesTheme {
                    Surface(color = Color.White) {
                        AutoSliding(list)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

}