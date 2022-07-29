package com.example.marvelheroes.screens.home.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelheroes.repositories.network.api.models.comicsModel.ResultComics
import com.example.marvelheroes.screens.home.ui.compose.components.CharacterCard
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBlue

@Composable
fun CharacterRowList(cards: List<ResultComics>) {
    val cardFiltered = cards.filter { !it.thumbnail.path.contains("available") }
    Column() {
        Text(
            text = "Comics",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow(modifier = Modifier.height(250.dp).background(darkBlue)) {
            items(cardFiltered) { card ->
                CharacterCard(card)
            }
        }
    }
}