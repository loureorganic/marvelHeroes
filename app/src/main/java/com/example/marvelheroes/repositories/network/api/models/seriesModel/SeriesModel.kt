package com.example.marvelheroes.repositories.network.api.models.seriesModel

data class SeriesModel(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)