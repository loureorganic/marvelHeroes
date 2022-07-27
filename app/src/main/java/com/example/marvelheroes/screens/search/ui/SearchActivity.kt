package com.example.marvelheroes.screens.search.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelheroes.R
import com.example.marvelheroes.repositories.network.api.models.characterModel.ResultCharacters
import com.example.marvelheroes.screens.home.ui.utils.loadPicture
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import com.example.marvelheroes.screens.search.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBackground
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBlue
import com.example.marvelheroes.screens.search.viewmodel.ViewModelSearch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelHome: ViewModelHome

    @Inject
    lateinit var viewModelSearch: ViewModelSearch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            viewModelSearch.searchCharacter(viewModelHome.searchTextState.value)
            val marvelListForSearchCharacter = viewModelSearch.marvelListForSearchCharacter.observeAsState(null)

            MarvelHeroesTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = darkBackground
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(8.dp)
                    ) {
                        title(viewModelHome.searchTextState.value)
                        Spacer(modifier = Modifier.height(8.dp))
                        marvelListForSearchCharacter.value?.let {
                            characterCard(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun title(searchedText: String) {
    Text(
        text = "Results for '$searchedText'",
        color = Color.White,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    )
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun characterCard(item: List<ResultCharacters>) {
    Column(modifier = Modifier.height(400.dp).background(darkBlue)) {
        Card(modifier = Modifier.height(300.dp).background(darkBlue)) {
            Box(
                modifier = Modifier
                    .background(darkBlue)
            ) {
                val url = item[0].thumbnail.path.replaceRange(
                    4,
                    4,
                    "s"
                ) + "/standard_fantastic.${item[0].thumbnail.extension}"
                val image =
                    loadPicture(
                        url = url,
                        defaultImage = R.drawable.marvel_heroes_placeholder
                    ).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "Description",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Card(modifier = Modifier.height(100.dp).background(darkBlue)) {
            Column(modifier = Modifier.background(darkBlue).padding(8.dp)) {
            Text(text = item[0].name, color = Color.White , fontSize = 24.sp, fontFamily = FontFamily.Default)
            Text(text = item[0].description, color = Color.White, maxLines = 2)
            }
        }
    }
}