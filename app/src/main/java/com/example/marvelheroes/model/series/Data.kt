package com.example.marvelheroes.model.series

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultSeries>,
    val total: Int
)
