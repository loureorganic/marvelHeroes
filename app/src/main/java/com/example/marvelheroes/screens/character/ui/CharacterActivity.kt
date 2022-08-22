package com.example.marvelheroes.screens.character.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.marvelheroes.screens.character.ui.pages.characterMainScreen
import com.example.marvelheroes.screens.character.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.character.viewmodel.ViewModelCharacter
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBackground
import com.example.marvelheroes.screens.search.viewmodel.ViewModelSearch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelSearch: ViewModelSearch

    @Inject
    lateinit var viewModelCharacter: ViewModelCharacter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelHeroesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = darkBackground) {
                    viewModelSearch.marvelListForSearchCharacter.value?.let { character ->
                        characterMainScreen(character, viewModelCharacter)
                    }
                }

                viewModelSearch.marvelListForSearchCharacter.value?.let { character ->
                    character[0].series.items.forEach { item ->
                        viewModelCharacter.getCharacterSeries(item.resourceURI)
                    }
                }

                viewModelSearch.marvelListForSearchCharacter.value?.let { character ->
                    character[0].comics.items.forEach { item ->
                        viewModelCharacter.getCharacterComics(item.resourceURI)
                    }
                }
            }
        }
    }
}
