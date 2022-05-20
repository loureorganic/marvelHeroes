package com.example.marvelheroes.screens.home.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.R
import com.example.marvelheroes.repositories.network.api.models.Result
import com.example.marvelheroes.screens.home.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.home.ui.utils.loadPicture
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelHome: ViewModelHome


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelHome.getCharacters()
        viewModelHome.marvelList.observe(this) { list ->
            setContent {
                MarvelHeroesTheme {
                    Surface(color = Color.White) {
                        CardList(list)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

    }

    @Composable
    fun CardList(list: List<Result>){
        Column (Modifier.background(Color.Red)){
            list.slice(0..3).map { item ->
                CardView(item)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    fun CardView(item: Result) {
            val url = item.thumbnail.path + "/portrait_xlarge.${item.thumbnail.extension}"
            val image = loadPicture(url = url, defaultImage = R.drawable.marvel_logo).value
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Black,
                modifier = Modifier.padding(8.dp)
            ) {
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "Recipe Featured Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }

}