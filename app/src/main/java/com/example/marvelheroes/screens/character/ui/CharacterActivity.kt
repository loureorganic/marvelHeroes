package com.example.marvelheroes.screens.character.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelheroes.screens.character.ui.composePages.characterMainScreen
import com.example.marvelheroes.screens.character.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBackground
import com.example.marvelheroes.screens.search.viewmodel.ViewModelSearch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CharacterActivity : ComponentActivity() {


    @Inject
    lateinit var viewModelSearch: ViewModelSearch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelHeroesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = darkBackground) {

                    viewModelSearch.marvelListForSearchCharacter.value?.let { character ->
                        characterMainScreen(character)
                    }
                }
            }
        }
    }
}

