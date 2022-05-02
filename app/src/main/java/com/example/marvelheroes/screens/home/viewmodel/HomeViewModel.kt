package com.example.marvelheroes.screens.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.repositories.network.api.models.MarvelApi
import com.example.marvelheroes.screens.home.services.ServicesHome
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface ViewModelHome {
    val listCharactersLiveData: MutableLiveData<Result<MarvelApi?>>
    fun getCharacters()
}

@AndroidEntryPoint
class HomeViewModel(private val services: ServicesHome) : ViewModel(), ViewModelHome {

    private val listCharactersResponse = MutableLiveData<Result<MarvelApi?>>()
    override val listCharactersLiveData: MutableLiveData<Result<MarvelApi?>> =
        listCharactersResponse

    override fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = services.getCharacters()
            listCharactersLiveData.postValue(result)
        }
    }
}