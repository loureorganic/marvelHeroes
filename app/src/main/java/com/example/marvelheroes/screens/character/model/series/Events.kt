package com.example.marvelheroes.screens.character.model.series

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)