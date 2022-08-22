package com.example.marvelheroes.repositories.network.api.models.seriesModel

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
