package com.example.marvelheroes.screens.search.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.repositories.network.api.models.characterModel.ResultCharacters
import com.example.marvelheroes.screens.search.services.ServicesSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelSearch {
    fun searchCharacter(nameSearched: String)
    val marvelListForSearchCharacter: MutableLiveData<List<ResultCharacters>>
    val errorMarvelListForSearchCharacter: MutableLiveData<Boolean>
}

@HiltViewModel
class SearchViewModel @Inject constructor(private val services: ServicesSearch) : ViewModelSearch,
    ViewModel() {

    private val errorCharacterSearch = MutableLiveData<Boolean>()
    override val errorMarvelListForSearchCharacter: MutableLiveData<Boolean> = errorCharacterSearch

    private val characterSearch = MutableLiveData<List<ResultCharacters>>()
    override val marvelListForSearchCharacter: MutableLiveData<List<ResultCharacters>> =
        characterSearch

    override fun searchCharacter(nameSearched: String) {
        viewModelScope.launch(Dispatchers.IO) {
            services.searchCharacter(nameSearched).collect { response ->
                runCatching {  }.onSuccess {
                    errorCharacterSearch.postValue(false)
                    marvelListForSearchCharacter.postValue(response.data.results)
                }
                    .onFailure { error ->
                        Log.e("MARVEL_HEROES - ERROR SEARCH_CHARACTER", ": $error" )
                        errorCharacterSearch.postValue(true)
                    }
            }
        }
    }

}