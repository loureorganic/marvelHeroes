package com.example.marvelheroes.repositories.network.api.models.characterModel

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)