package com.example.marvelheroes.repositories.network.api.models.comicsModel

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)
