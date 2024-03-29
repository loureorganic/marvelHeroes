package com.example.marvelheroes.screens.home.ui.compose.mainScreen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.screens.home.ui.compose.CharacterRowList
import com.example.marvelheroes.screens.home.ui.compose.SeriesRowList
import com.example.marvelheroes.screens.home.ui.compose.components.AutoSliding
import com.example.marvelheroes.screens.home.ui.compose.components.IconListCategoriesComponent
import com.example.marvelheroes.screens.home.ui.compose.components.MainAppBar
import com.example.marvelheroes.screens.home.ui.compose.elements.notificationIcon
import com.example.marvelheroes.screens.home.ui.utils.SearchWidgetState
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import com.example.marvelheroes.screens.search.ui.SearchActivity
import javax.inject.Singleton

@Singleton
@Composable
fun MainScreen(viewModelHome: ViewModelHome, context: Context, function: (a: Intent) -> (Unit)) {
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
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier.weight(5f), verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
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
                        function(Intent(context, SearchActivity::class.java))
                    },
                    onSearchTriggered = {
                        viewModelHome.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                    },
                    context = context,
                    function = function,
                )
            }
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                notificationIcon()
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            IconListCategoriesComponent()
        }
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
