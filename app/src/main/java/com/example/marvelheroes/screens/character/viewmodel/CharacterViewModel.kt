package com.example.marvelheroes.screens.character.viewmodel

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
    val marvelListCharaterSeries: MutableLiveData<List<ResultSeries>>
    val errorMarvelListCharaterSeries: MutableLiveData<Boolean>

}

@HiltViewModel
class CharacterViewModel @Inject constructor(private val services: ServicesCharacter) : ViewModel(),
    ViewModelCharacter {


    private val characterSeries = MutableLiveData<List<ResultSeries>>()
    override val marvelListCharaterSeries: MutableLiveData<List<ResultSeries>> = characterSeries

    private val errorCharacterSeries = MutableLiveData<Boolean>()
    override val errorMarvelListCharaterSeries: MutableLiveData<Boolean> = errorCharacterSeries

    override fun getCharacterSeries(resourceURI: String) {
        viewModelScope.launch(Dispatchers.IO) {
            services.getCharacterSeries(resourceURI).collect { result ->
                runCatching { }
                    .onSuccess {
                        marvelListCharaterSeries.postValue(result.data.results)
                        errorMarvelListCharaterSeries.postValue(false)
                    }
                    .onFailure {
                        marvelListCharaterSeries.postValue(result.data.results)
                        errorMarvelListCharaterSeries.postValue(true)
                    }
            }

        }
    }


}