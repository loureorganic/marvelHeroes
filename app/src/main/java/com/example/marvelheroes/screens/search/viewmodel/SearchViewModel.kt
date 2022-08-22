package com.example.marvelheroes.screens.search.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.repositories.network.api.models.characterModel.ResultCharacters
import com.example.marvelheroes.screens.home.ui.utils.SearchWidgetState
import com.example.marvelheroes.screens.search.services.ServicesSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelSearch {
    fun searchCharacter(nameSearched: String)
    val searchCopyrightData: MutableLiveData<String>
    val marvelListForSearchCharacter: MutableLiveData<List<ResultCharacters>>
    val errorMarvelListForSearchCharacter: MutableLiveData<Boolean>
    val searchWidgetState: State<SearchWidgetState>
    val searchTextState: State<String>
    fun updateSearchWidgetState(newValue: SearchWidgetState)
    fun updateSearchTextState(newValue: String)
}

@HiltViewModel
class SearchViewModel @Inject constructor(private val services: ServicesSearch) : ViewModelSearch,
    ViewModel() {

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

    private val errorCharacterSearch = MutableLiveData<Boolean>()
    override val errorMarvelListForSearchCharacter: MutableLiveData<Boolean> = errorCharacterSearch

    private val characterSearch = MutableLiveData<List<ResultCharacters>>()
    override val marvelListForSearchCharacter: MutableLiveData<List<ResultCharacters>> =
        characterSearch

    private val copyrightData = MutableLiveData<String>()
    override val searchCopyrightData: MutableLiveData<String> = copyrightData

    override fun searchCharacter(nameSearched: String) {
        viewModelScope.launch(Dispatchers.IO) {
            services.searchCharacter(nameSearched).collect { response ->
                runCatching { }.onSuccess {
                    errorCharacterSearch.postValue(false)
                    searchCopyrightData.postValue(response.attributionText)
                    marvelListForSearchCharacter.postValue(response.data.results)
                }
                    .onFailure { error ->
                        Log.e("MARVEL_HEROES - ERROR SEARCH_CHARACTER", ": $error")
                        errorCharacterSearch.postValue(true)
                    }
            }
        }
    }
}
