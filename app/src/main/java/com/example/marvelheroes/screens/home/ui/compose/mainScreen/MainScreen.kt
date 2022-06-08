package com.example.marvelheroes.screens.home.ui.compose.mainScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.screens.home.ui.compose.AutoSliding
import com.example.marvelheroes.screens.home.ui.compose.CharacterRowList
import com.example.marvelheroes.screens.home.ui.compose.MainAppBar
import com.example.marvelheroes.screens.home.ui.compose.SeriesRowList
import com.example.marvelheroes.screens.home.ui.utils.SearchWidgetState
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import javax.inject.Singleton

@Singleton
@Composable
fun MainScreen(viewModelHome: ViewModelHome) {


    val marvelListForSliding = viewModelHome.marvelListForSliding.observeAsState(null)
    val errorMarvelListForSliding = viewModelHome.errorMarvelListForSliding.observeAsState(null)

    val marvelListAllComicsResult = viewModelHome.marvelListAllComics.observeAsState(null)
    val errorMarvelListAllComics = viewModelHome.errorMarvelListAllComics.observeAsState(null)

    val marvelListAllSeries = viewModelHome.marvelListAllSeries.observeAsState(null)
    val errorMarvelListAllSeries = viewModelHome.errorMarvelListAllSeries.observeAsState(null)


    val searchWidgetState by viewModelHome.searchWidgetState
    val searchTextState by viewModelHome.searchTextState

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        MainAppBar(
            searchWidgetState = searchWidgetState,
            searchTextState = searchTextState,
            onTextChange = {
                viewModelHome.updateSearchTextState(newValue = it)
            },
            onCloseClicked = {
                viewModelHome.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
            },
            onSearchClicked = {
                Log.d("Searched Text", it)
            },
            onSearchTriggered = {
                viewModelHome.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
            }
        )

        errorMarvelListForSliding.value?.let { resultError ->
            if (resultError) {
                Text(text = "Bad request. Try later soon!")
            } else {
                marvelListForSliding.value?.let { AutoSliding(it) }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        errorMarvelListAllComics.value?.let { resultError ->
            if (resultError) {
                Text(text = "Bad request. Try later soon!")
            } else {
                marvelListAllComicsResult.value?.let { CharacterRowList(cards = it) }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))

        errorMarvelListAllSeries.value?.let { resultError ->
            if (resultError) {
                Text(text = "Bad request. Try later soon!")
            } else {
                marvelListAllSeries.value?.let { SeriesRowList(it) }
            }
        }
    }
}