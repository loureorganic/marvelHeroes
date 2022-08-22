package com.example.marvelheroes.repositories.network.api.models.characterModel

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)
