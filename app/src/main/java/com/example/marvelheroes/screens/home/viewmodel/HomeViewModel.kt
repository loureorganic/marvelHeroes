package com.example.marvelheroes.screens.home.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.repositories.network.api.models.characterModel.ResultCharacters
import com.example.marvelheroes.repositories.network.api.models.comicsModel.ResultComics
import com.example.marvelheroes.screens.home.services.ServicesHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelHome {
    fun getCharactersForSliding()
    fun getAllCharacters()
    fun getAllComics()
    val marvelList: MutableLiveData<List<ResultCharacters>>
    val marvelListAllCharacters: MutableLiveData<List<ResultCharacters>>
    val marvelListAllComics: MutableLiveData<List<ResultComics>>
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val services: ServicesHome) : ViewModel(),
    ViewModelHome {

    private val marvelApi = MutableLiveData<List<ResultCharacters>>()
    private val comicsCall = MutableLiveData<List<ResultComics>>()

    override val marvelList: MutableLiveData<List<ResultCharacters>> = marvelApi

    override val marvelListAllCharacters: MutableLiveData<List<ResultCharacters>> = marvelApi
    override val marvelListAllComics: MutableLiveData<List<ResultComics>> = comicsCall

    override fun getCharactersForSliding() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getCharactersForSliding().collect { response ->
                marvelList.postValue(response.data.results)
            }
        }
    }

    override fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO){
            services.getAllCharacters().collect { response ->
                marvelListAllCharacters.postValue(response.data.results)
            }
        }
    }

    override fun getAllComics(){
        viewModelScope.launch(Dispatchers.IO){
            services.getAllComics().collect { response ->
                marvelListAllComics.postValue(response.data.results)
            }
        }
    }

}