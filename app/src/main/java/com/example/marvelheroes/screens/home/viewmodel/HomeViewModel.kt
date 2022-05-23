package com.example.marvelheroes.screens.home.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.repositories.network.api.models.Result
import com.example.marvelheroes.screens.home.services.ServicesHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelHome {
    fun getCharacters()
    val marvelList: MutableLiveData<List<Result>>
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val services: ServicesHome) : ViewModel(),
    ViewModelHome {

    private val marvelApi = MutableLiveData<List<Result>>()
    override val marvelList: MutableLiveData<List<Result>> = marvelApi
    override fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getCharacters().collect { response ->
                marvelList.postValue(response.data.results)
            }
        }
    }
}