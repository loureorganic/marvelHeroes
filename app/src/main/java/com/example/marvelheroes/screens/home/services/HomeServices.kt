package com.example.marvelheroes.screens.home.services

import com.example.marvelheroes.repositories.network.api.models.MarvelApi
import com.example.marvelheroes.screens.home.repository.RepositoryHome
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

interface ServicesHome{
    suspend fun getCharacters() : Result<MarvelApi?>
}

@AndroidEntryPoint
class HomeServices(private val repository: RepositoryHome): ServicesHome {
    override suspend fun getCharacters(): Result<MarvelApi?> {
        return repository.getCharacters().map { it.body() }
    }
}