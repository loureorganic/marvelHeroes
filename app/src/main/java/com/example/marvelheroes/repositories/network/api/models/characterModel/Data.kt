package com.example.marvelheroes.repositories.network.api.models.characterModel

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultCharacters>,
    val total: Int
)