package com.example.marvelheroes.screens.character.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelheroes.screens.character.ui.composePages.characterMainScreen
import com.example.marvelheroes.screens.character.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.character.viewmodel.ViewModelCharacter
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
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
                    Log.i("INFORMATION", "SERIE ${character[0].series.items.size}")
                    character[0].series.items.forEach { item ->
                        //Cada item de serie fará uma requisição
                        viewModelCharacter.getCharacterSeries(item.resourceURI)
                    }
                }
            }
        }
    }
}

