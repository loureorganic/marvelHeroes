package com.example.marvelheroes.screens.home.viewmodel


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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelHome {
    fun getCharactersForSliding()
    fun getAllCharacters()
    fun getAllComics()
    fun getAllSeries()
    val marvelList: MutableLiveData<List<ResultCharacters>>
    val marvelListAllCharacters: MutableLiveData<List<ResultCharacters>>
    val marvelListAllComics: MutableLiveData<List<ResultComics>>
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
    private val comicsCall = MutableLiveData<List<ResultComics>>()
    private val seriesCall = MutableLiveData<List<ResultSeries>>()
    private val errorSeriesCall = MutableLiveData<Boolean>()

    override val marvelList: MutableLiveData<List<ResultCharacters>> = marvelApi
    override val marvelListAllCharacters: MutableLiveData<List<ResultCharacters>> = marvelApi
    override val marvelListAllComics: MutableLiveData<List<ResultComics>> = comicsCall
    override val marvelListAllSeries: MutableLiveData<List<ResultSeries>> = seriesCall
    override val errorMarvelListAllSeries: MutableLiveData<Boolean> = errorSeriesCall


    override fun getCharactersForSliding() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getCharactersForSliding().filter { true }.collect { response ->
                marvelList.postValue(response.data.results)
            }
        }
    }

    override fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getAllCharacters().filter { true }.collect { response ->
                marvelListAllCharacters.postValue(response.data.results)
            }
        }
    }

    override fun getAllComics() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getAllComics().filter { true }.collect { response ->
                marvelListAllComics.postValue(response.data.results)
            }
        }
    }

    override fun getAllSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            services.getAllSeries().collect { responseTest ->
                runCatching {}
                    .onFailure { errorSeriesCall.postValue(true) }
                    .onSuccess {
                        errorSeriesCall.postValue(false)
                        marvelListAllSeries.postValue(responseTest.data.results)
                    }
            }
        }
    }
}