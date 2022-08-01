package com.example.marvelheroes.screens.character.model.series

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultSeries>,
    val total: Int
)