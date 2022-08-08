package com.example.marvelheroes.model.resultComics

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)