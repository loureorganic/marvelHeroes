package com.example.marvelheroes.screens.search.ui.composePages

import android.content.Context
import android.content.Intent
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
import com.example.marvelheroes.screens.home.ui.HomeActivity
import com.example.marvelheroes.screens.home.ui.compose.MainAppBar
import com.example.marvelheroes.screens.home.ui.utils.SearchWidgetState
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import com.example.marvelheroes.screens.search.ui.SearchActivity
import com.example.marvelheroes.screens.search.ui.components.characterCard
import com.example.marvelheroes.screens.search.ui.components.searchTitle
import com.example.marvelheroes.screens.search.viewmodel.ViewModelSearch

@Composable
fun searchMainScreen(
    viewModelSearch: ViewModelSearch,
    viewModelHome: ViewModelHome,
    context: Context,
    function: (a: Intent) -> (Unit)
) {

    if (viewModelHome.searchTextState.value.isNotEmpty()) {
        viewModelSearch.searchCharacter(viewModelHome.searchTextState.value)
    }
    val marvelListForSearchCharacter =
        viewModelSearch.marvelListForSearchCharacter.observeAsState(null)
    val errorMarvelListForSearchCharacter =
        viewModelSearch.errorMarvelListForSearchCharacter.observeAsState(null)
    val searchCopyrightData = viewModelSearch.searchCopyrightData.observeAsState(null)


    val searchWidgetState by viewModelHome.searchWidgetState
    val searchTextState by viewModelHome.searchTextState


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        MainAppBar(
            context = context,
            function = function,
            searchWidgetState = searchWidgetState,
            searchTextState = searchTextState,
            onTextChange = {
                viewModelHome.updateSearchTextState(newValue = it)
            },
            onCloseClicked = {
                viewModelHome.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
            },
            onSearchClicked = {
                function(Intent(context, SearchActivity::class.java))
            },
            onSearchTriggered = {
                viewModelHome.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
            }
        )
        searchTitle(viewModelHome.searchTextState.value)
        Spacer(modifier = Modifier.height(8.dp))
        errorMarvelListForSearchCharacter.value?.let { resultError ->
            if (resultError) {
                Text(text = "Bad request. Try later soon!")
            } else {
                searchCopyrightData.value?.let { copyrightData ->
                    marvelListForSearchCharacter.value?.let { resultCharacter ->
                        if (resultCharacter.isEmpty()) {

                        } else {
                            characterCard( resultCharacter, copyrightData)
                        }
                    }
                }
            }
        }
    }
}