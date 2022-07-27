package com.example.marvelheroes.screens.home.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.marvelheroes.screens.home.ui.compose.mainScreen.MainScreen
import com.example.marvelheroes.screens.home.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBackground
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelHome: ViewModelHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelHome.getCharactersForSliding()
        viewModelHome.getAllComics()
        viewModelHome.getAllSeries()
        setContent {
            MarvelHeroesTheme {
                Surface(
                    color = darkBackground
                ) {
                    MainScreen(viewModelHome = viewModelHome, this@HomeActivity, ::startActivity)
                }
            }
        }
    }
}

