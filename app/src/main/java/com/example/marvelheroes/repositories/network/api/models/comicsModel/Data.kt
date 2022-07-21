package com.example.marvelheroes.repositories.network.api.models.comicsModel

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultComics>,
    val total: Int
)