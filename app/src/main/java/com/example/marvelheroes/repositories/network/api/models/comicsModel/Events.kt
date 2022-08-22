package com.example.marvelheroes.repositories.network.api.models.comicsModel

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)
