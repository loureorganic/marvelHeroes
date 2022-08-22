package com.example.marvelheroes.screens.character.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabItem(selected: Boolean, index: Int, pair: String, pagerState: PagerState) {

    val coroutineScope = rememberCoroutineScope()

    Tab(
        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
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
                .align(Alignment.CenterHorizontally)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .height(30.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(pair, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
