package com.example.marvelheroes.screens.home.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.repositories.network.api.models.characterModel.ResultCharacters
import com.example.marvelheroes.repositories.network.api.models.comicsModel.ResultComics
import com.example.marvelheroes.repositories.network.api.models.seriesModel.ResultSeries
import com.example.marvelheroes.screens.home.services.ServicesHome
import com.example.marvelheroes.screens.home.ui.utils.SearchWidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelHome {
    fun getCharactersForSliding()
    fun getAllCharacters()
    fun getAllComics()
    fun getAllSeries()
    val marvelListForSliding: MutableLiveData<List<ResultCharacters>>
    val errorMarvelListForSliding: MutableLiveData<Boolean>
    val marvelListAllCharacters: MutableLiveData<List<ResultCharacters>>
    val errorMarvelListAllCharacters: MutableLiveData<Boolean>
    val marvelListAllComics: MutableLiveData<List<ResultComics>>
    val errorMarvelListAllComics: MutableLiveData<Boolean>
    val marvelListAllSeries: MutableLiveData<List<ResultSeries>>
    val errorMarvelListAllSeries: MutableLiveData<Boolean>
    val searchWidgetState: State<SearchWidgetState>
    val searchTextState: State<String>
    fun updateSearchWidgetState(newValue: SearchWidgetState)
    fun updateSearchTextState(newValue: String)
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val services: ServicesHome) : ViewModel(),
    ViewModelHome {

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    override val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")

    override val searchTextState: State<String> = _searchTextState

    override fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    override fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }


    private val marvelApi = MutableLiveData<List<ResultCharacters>>()
    private val errorMarvelSlidingCall = MutableLiveData<Boolean>()
    private val errorMarvelCharactersCall = MutableLiveData<Boolean>()

    private val comicsCall = MutableLiveData<List<ResultComics>>()
    private val errorComicsCall = MutableLiveData<Boolean>()

    private val seriesCall = MutableLiveData<List<ResultSeries>>()
    private val errorSeriesCall = MutableLiveData<Boolean>()


    override val marvelListForSliding: MutableLiveData<List<ResultCharacters>> = marvelApi
    override val errorMarvelListForSliding: MutableLiveData<Boolean> = errorMarvelSlidingCall

    override val marvelListAllCharacters: MutableLiveData<List<ResultCharacters>> = marvelApi
    override val errorMarvelListAllCharacters: MutableLiveData<Boolean> = errorMarvelCharactersCall

    override val marvelListAllComics: MutableLiveData<List<ResultComics>> = comicsCall
    override val errorMarvelListAllComics: MutableLiveData<Boolean> = errorComicsCall

    override val marvelListAllSeries: MutableLiveData<List<ResultSeries>> = seriesCall
    override val errorMarvelListAllSeries: MutableLiveData<Boolean> = errorSeriesCall


    override fun getCharactersForSliding() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getCharactersForSliding().collect { response ->
                runCatching {}
                    .onFailure { error ->
                        errorMarvelListForSliding.postValue(true)
                        Log.e("MARVEL_HEROES - ERROR GET_CHARACTERS_FOR_SLIDING", ": $error")
                    }
                    .onSuccess { marvelListForSliding.postValue(response.data.results)
                        errorMarvelListForSliding.postValue(false)}

            }
        }
    }

    override fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getAllCharacters().collect { response ->
                runCatching {}
                    .onFailure { error ->
                        errorMarvelListAllCharacters.postValue(true)
                        Log.e("MARVEL_HEROES - ERROR GET_ALL_CHARACTERS", ": $error")
                    }
                    .onSuccess { marvelListAllCharacters.postValue(response.data.results)
                        errorMarvelListAllCharacters.postValue(false)}

            }
        }
    }

    override fun getAllComics() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getAllComics().collect { response ->
                runCatching { }
                    .onFailure { error ->
                        errorMarvelListAllComics.postValue(true)
                        Log.e("MARVEL_HEROES - ERROR GET_COMICS", ": $error")
                    }
                    .onSuccess {
                        marvelListAllComics.postValue(response.data.results)
                        errorMarvelListAllComics.postValue(false)
                    }
            }
        }
    }

    override fun getAllSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getAllSeries().collect { response ->
                runCatching {}
                    .onFailure { error ->
                        errorMarvelListAllSeries.postValue(true)
                        Log.e("MARVEL_HEROES - ERROR GET_SERIES", ": $error")}
                    .onSuccess {
                        errorMarvelListAllSeries.postValue(false)
                        marvelListAllSeries.postValue(response.data.results)
                    }
            }
        }
    }
}