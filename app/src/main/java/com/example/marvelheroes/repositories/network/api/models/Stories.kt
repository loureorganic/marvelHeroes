package com.example.marvelheroes.repositories.network.api.models

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)