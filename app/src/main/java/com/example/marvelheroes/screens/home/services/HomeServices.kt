package com.example.marvelheroes.screens.home.services

import com.example.marvelheroes.repositories.network.api.models.MarvelApi
import com.example.marvelheroes.screens.home.repository.RepositoryHome

interface ServicesHome {
    suspend fun getCharacters(): Result<MarvelApi?>
}

class HomeServices(private val repository: RepositoryHome) : ServicesHome {
    override suspend fun getCharacters(): Result<MarvelApi?> {
        return repository.getCharacters().map { it.body() }
    }
}