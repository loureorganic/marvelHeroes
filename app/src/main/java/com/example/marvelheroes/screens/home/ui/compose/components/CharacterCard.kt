package com.example.marvelheroes.screens.home.ui.compose.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.R
import com.example.marvelheroes.repositories.network.api.models.comicsModel.ResultComics
import com.example.marvelheroes.screens.home.ui.utils.loadPicture
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBackground
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("ResourceType")
@Composable
fun CharacterCard(card: ResultComics) {
    Box(modifier = Modifier.background(darkBackground).padding(8.dp).width(140.dp)) {
        val url = card.thumbnail.path.replaceRange(
            4,
            4,
            "s"
        ) + "/portrait_uncanny.${card.thumbnail.extension}"
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
        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        /*Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(Color.White)
                .wrapContentWidth()
        ) {
            Text(text = card.title)
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
        }*/
    }
}