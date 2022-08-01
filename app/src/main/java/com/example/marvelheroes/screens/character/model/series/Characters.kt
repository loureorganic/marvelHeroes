package com.example.marvelheroes.screens.character.model.series

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)