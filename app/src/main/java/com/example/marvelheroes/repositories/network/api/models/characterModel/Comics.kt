package com.example.marvelheroes.repositories.network.api.models.characterModel

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)