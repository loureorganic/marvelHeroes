package com.example.marvelheroes.repositories.network.api.request

import com.example.marvelheroes.repositories.network.api.utils.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MarvelApiRequest by lazy{
        retrofit.create(MarvelApiRequest::class.java)
    }
}