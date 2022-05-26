package com.example.marvelheroes.screens.home.ui.compose

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.repositories.network.api.models.comicsModel.ResultComics

@Composable
fun CharacterRowList(cards: List<ResultComics>) {
    LazyRow(modifier = Modifier.height(250.dp)){
        items(cards){ card ->
            CharacterCard(card)
        }
    }
}