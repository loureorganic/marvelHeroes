package com.example.marvelheroes.repositories.network.api.models.storiesModel

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)