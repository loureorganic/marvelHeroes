package com.example.marvelheroes.repositories.network.api.models.seriesModel

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultSeries>,
    val total: Int
)
