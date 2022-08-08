package com.example.marvelheroes.repositories.network.api.request

import com.example.marvelheroes.repositories.network.api.models.characterModel.MarvelApi
import com.example.marvelheroes.repositories.network.api.models.comicsModel.ComicsModel
import com.example.marvelheroes.repositories.network.api.models.seriesModel.SeriesModel
import com.example.marvelheroes.repositories.network.api.models.storiesModel.StorieModel
import com.example.marvelheroes.repositories.network.api.utils.ApiConstants.PUBLIC_KEY
import com.example.marvelheroes.screens.character.model.series.seriesOfCharacter
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiRequest {
    @GET("/v1/public/characters")
    suspend fun getCharactersForSliding(
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("hash") hash: String,
        @Query("limit") count: Int = 7,
        @Query("offset") offset: Int = 2
    ): MarvelApi

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("hash") hash: String,
        @Query("limit") count: Int = 60,
        @Query("offset") offset: Int = 0,
    ): MarvelApi

    @GET("/v1/public/characters/")
    suspend fun getAnCharacters(
        @Path("characterId") characterId : String,
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("hash") hash: String,
    ): MarvelApi

    @GET("/v1/public/comics")
    suspend fun getAllComics(
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("hash") hash: String,
        @Query("limit") count: Int = 80,
    ) : ComicsModel

    @GET("/v1/public/series")
    suspend fun getAllSeries(
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("hash") hash: String,
        @Query("limit") count: Int = 80,
    ) : SeriesModel

    @GET("/v1/public/characters")
    suspend fun searchCharacter(
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("hash") hash: String,
        @Query("limit") count: Int = 60,
        @Query("offset") offset: Int = 0,
        @Query("name") name: String
    ): MarvelApi

    @GET("/v1/public/series")
    suspend fun getCharacterSeries(
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("hash") hash: String,
        @Query("limit") count: Int = 60,
        @Query("offset") offset: Int = 0,
        @Query("id") id: String
    ): seriesOfCharacter

}
