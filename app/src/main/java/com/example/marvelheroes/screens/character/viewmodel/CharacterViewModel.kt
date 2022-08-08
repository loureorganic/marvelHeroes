package com.example.marvelheroes.screens.character.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.screens.character.model.series.ResultSeries
import com.example.marvelheroes.screens.character.services.ServicesCharacter
import com.example.marvelheroes.screens.home.repository.RepositoryHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelCharacter {
    fun getCharacterSeries(resourceURI: String)
    val marvelListCharacterComics: MutableLiveData<ArrayList<List<ResultSeries>>>
    val marvelListCharaterSeries: MutableLiveData<ArrayList<List<ResultSeries>>>
    val errorMarvelListCharaterSeries: MutableLiveData<Boolean>
    fun getValuesOfMarvelListCharacterSeries()
}

@HiltViewModel
class CharacterViewModel @Inject constructor(private val services: ServicesCharacter) : ViewModel(),
    ViewModelCharacter {


    val list:ArrayList<List<ResultSeries>> = ArrayList<List<ResultSeries>>()

    private val characterSeries = MutableLiveData<ArrayList<List<ResultSeries>>>()
    override val marvelListCharaterSeries: MutableLiveData<ArrayList<List<ResultSeries>>> = characterSeries

    private val characterComics = MutableLiveData<ArrayList<List<ResultSeries>>>()
    override val marvelListCharacterComics: MutableLiveData<ArrayList<List<ResultSeries>>> = characterComics


    private val errorCharacterSeries = MutableLiveData<Boolean>()
    override val errorMarvelListCharaterSeries: MutableLiveData<Boolean> = errorCharacterSeries

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

    override fun getValuesOfMarvelListCharacterSeries(){
        marvelListCharaterSeries.postValue(list)
    }

    fun getValuesOfMarvelListCharacterComics(){

    }
}