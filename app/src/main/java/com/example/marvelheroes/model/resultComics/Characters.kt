package com.example.marvelheroes.model.resultComics

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)