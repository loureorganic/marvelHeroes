package com.example.marvelheroes.screens.character.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.model.comics.ResultComics
import com.example.marvelheroes.model.series.ResultSeries
import com.example.marvelheroes.screens.character.services.ServicesCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelCharacter {
    fun getCharacterSeries(resourceURI: String)
    fun getCharacterComics(resourceURI: String)
    fun getValuesOfMarvelListCharacterSeries()
    fun getValuesOfMarvelListCharacterComics()
    fun clearSeriesList()
    fun clearComicsList()
    val marvelListCharacterComics: MutableLiveData<ArrayList<List<ResultComics>>>
    val marvelListCharacterSeries: MutableLiveData<ArrayList<List<ResultSeries>>>
    val errorMarvelListCharacterSeries: MutableLiveData<Boolean>
}

@HiltViewModel
class CharacterViewModel @Inject constructor(private val services: ServicesCharacter) :
    ViewModel(),
    ViewModelCharacter {

    private val list: ArrayList<List<ResultSeries>> = ArrayList()
    private val comicsList: ArrayList<List<ResultComics>> = ArrayList()

    private val characterSeries = MutableLiveData<ArrayList<List<ResultSeries>>>()
    override val marvelListCharacterSeries: MutableLiveData<ArrayList<List<ResultSeries>>> =
        characterSeries

    private val characterComics = MutableLiveData<ArrayList<List<ResultComics>>>()
    override val marvelListCharacterComics: MutableLiveData<ArrayList<List<ResultComics>>> =
        characterComics

    private val errorCharacterSeries = MutableLiveData<Boolean>()
    override val errorMarvelListCharacterSeries: MutableLiveData<Boolean> = errorCharacterSeries

    override fun getCharacterSeries(resourceURI: String) {
        viewModelScope.launch(Dispatchers.IO) {
            services.getCharacterSeries(resourceURI).collect { result ->
                runCatching { }
                    .onSuccess {
                        list.add(result.data.results)
                    }
                    .onFailure {
                    }
            }
        }
    }

    override fun getValuesOfMarvelListCharacterSeries() {
        marvelListCharacterSeries.postValue(list)
    }

    override fun getCharacterComics(resourceURI: String) {
        viewModelScope.launch(Dispatchers.IO) {
            services.getCharacterComics(resourceURI).collect { result ->
                runCatching { }
                    .onSuccess {
                        comicsList.add(result.data.results)
                    }
                    .onFailure {
                    }
            }
        }
    }

    override fun getValuesOfMarvelListCharacterComics() {
        marvelListCharacterComics.postValue(comicsList)
    }

    override fun clearSeriesList() {
        list.clear()
    }

    override fun clearComicsList() {
        comicsList.clear()
    }
}
