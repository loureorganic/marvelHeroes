package com.example.marvelheroes.repositories.network.api.models.storiesModel

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)
