package com.example.marvelheroes.screens.home.ui.compose

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
import com.example.marvelheroes.repositories.network.api.models.seriesModel.ResultSeries


@Composable
fun SeriesRowList(cards: List<ResultSeries>) {
    val cardFiltered = cards.filter { !it.thumbnail.path.contains("available") }
    Column() {
        Text(
            text = "Series",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow(modifier = Modifier.height(250.dp)) {
            items(cardFiltered) { card ->
                SerieCard(card)
            }
        }
    }
}