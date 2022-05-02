package com.example.marvelheroes.repositories.network.api.request

import com.example.marvelheroes.repositories.network.api.utils.ApiConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    fun  initRetrofit() : Retrofit{
      return  Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(OkHttpClient().newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

   val api: MarvelApiRequest by lazy{
       initRetrofit().create(MarvelApiRequest::class.java)
    }
}