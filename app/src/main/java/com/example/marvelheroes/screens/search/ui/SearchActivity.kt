package com.example.marvelheroes.screens.search.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import com.example.marvelheroes.screens.search.ui.composePages.searchMainScreen
import com.example.marvelheroes.screens.search.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBackground
import com.example.marvelheroes.screens.search.viewmodel.ViewModelSearch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelHome: ViewModelHome

    @Inject
    lateinit var viewModelSearch: ViewModelSearch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelHeroesTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = darkBackground
                ) {
                    searchMainScreen(
                        viewModelSearch = viewModelSearch,
                        viewModelHome = viewModelHome,
                        context = this,
                        function = ::startActivity
                    )
                }
            }
        }
    }
}
