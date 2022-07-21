package com.example.marvelheroes.repositories.network.api.models.comicsModel

data class ComicsModel(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)