package com.example.marvelheroes.screens.character.model.character

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)