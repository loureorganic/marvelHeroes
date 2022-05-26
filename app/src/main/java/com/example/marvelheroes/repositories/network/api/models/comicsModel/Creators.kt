package com.example.marvelheroes.repositories.network.api.models.comicsModel

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)