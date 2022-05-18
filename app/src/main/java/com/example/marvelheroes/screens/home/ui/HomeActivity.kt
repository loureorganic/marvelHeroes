package com.example.marvelheroes.screens.home.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.marvelheroes.R
import com.example.marvelheroes.screens.home.adapters.PhotoAdapter
import com.example.marvelheroes.screens.home.ui.model.natural
import com.example.marvelheroes.screens.home.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.home.ui.ui.theme.Purple500
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import com.google.accompanist.pager.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import javax.inject.Inject
import kotlin.math.absoluteValue


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelHome: ViewModelHome

    @Inject
    lateinit var photoAdapter : PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelHeroesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AutoSliding()
                }

            }
        }
    }

    override fun onStart() {
        super.onStart()

    }


    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun AutoSliding() {
        val pagerState = rememberPagerState(
            pageCount = natural.size,
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

            Column(modifier = Modifier.height(70.dp)) {
                Image(painter = painterResource(id =  R.drawable.marvel_logo), contentDescription = "Marvel Logo", modifier = Modifier.padding(8.dp)  )
            }
            Column(modifier = Modifier.height(380.dp)) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .padding(0.dp, 40.dp, 0.dp, 40.dp)
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
                        val natural = natural[page]
                        //Box doesn't appear when change the color
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Red)
                        ) {
                            Image(
                                painter = painterResource(
                                    id = when (page) {
                                        1 -> R.drawable.image_1
                                        2 -> R.drawable.image_2
                                        else -> R.drawable.image_3
                                    }
                                ),
                                contentDescription = "Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(15.dp)
                            ) {
                                Text(
                                    text = natural.title,
                                    style = MaterialTheme.typography.h5,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = natural.desc,
                                    style = MaterialTheme.typography.body1,
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                                )
                            }
                        }
                    }
                }
                // Indicator how image we are
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }
        }
    }
}