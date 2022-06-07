package com.example.marvelheroes.repositories.network.api.models.storiesModel

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val result: List<ResultStories>,
    val total: Int
)