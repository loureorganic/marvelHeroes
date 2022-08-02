package com.example.marvelheroes.screens.character.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.repositories.network.api.models.comicsModel.Series
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBackground
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBlue
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@Composable
fun ContentView() {

}



@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsRow(pagerState: PagerState) {
    val tabTextandIcon = listOf(
        "tab 1", "tab 2", "tab 3"
    )

    val coroutineScope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = darkBlue, contentColor = darkBlue, divider = {
            TabRowDefaults.Divider(
                thickness = 3.dp,
                color = Color.Black
            )
        }, indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 3.dp,
                color = Color.Yellow
            )
        }) {
        tabTextandIcon.forEachIndexed { index, pair ->

            val selected = pagerState.currentPage == index

            Tab(
                selected = selected,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                enabled = true,
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        //Imagem aqui
                    }

                }


            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun PagerContent(pagerState: PagerState){
    CompositionLocalProvider( LocalOverScrollConfiguration provides null) {
        Box(modifier = Modifier.fillMaxSize()){
          HorizontalPager( state = pagerState) {
               page ->
              when (page){
                  0 -> {
                      AboutSection()
                  }
                  1  -> {
                   SeriesSection()
                  }
                  2 -> {
                      ComicsSection()
                  }
              }
          }
        }
    }
}

@Composable
fun AboutSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground)
    ) {
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Text("About")
    }
}

@Composable
fun SeriesSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground)
    ) {
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Text("Series")
    }
}

@Composable
fun ComicsSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground)
    ) {
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Text("Comic s")
    }
}