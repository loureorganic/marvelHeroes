package com.example.marvelheroes.screens.home.ui.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.marvelheroes.R
import com.example.marvelheroes.repositories.network.api.models.characterModel.ResultCharacters
import com.example.marvelheroes.screens.home.ui.utils.loadPicture
import com.google.accompanist.pager.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@SuppressLint("ResourceType")
@OptIn(ExperimentalPagerApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun AutoSliding(list: List<ResultCharacters>) {
    val listFiltered = list.filter { !it.thumbnail.path.contains("available") }
    val pagerState = rememberPagerState(
        pageCount = listFiltered.size,
        initialOffscreenLimit = 2
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(1500)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }
    Column() {

        Column(modifier = Modifier.height(320.dp)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 20.dp, 0.dp, 20.dp)
            ) { page ->
                Card(
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                        }
                        .fillMaxWidth()
                        .padding(15.dp, 0.dp, 15.dp, 0.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        val img2 = listFiltered[page]
                        val url = img2.thumbnail.path.replaceRange(
                            4,
                            4,
                            "s"
                        ) + "/portrait_uncanny.${img2.thumbnail.extension}"
                        val image = loadPicture(url = url, defaultImage = R.drawable.marvel_heroes_placeholder).value
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
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .height(48.dp)
                            .background(Color.White)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = list[page].name,
                            style = MaterialTheme.typography.h5,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            // Indicator how image we are
            /*    HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )*/
        }
    }
}