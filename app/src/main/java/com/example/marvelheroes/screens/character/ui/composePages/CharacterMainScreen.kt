package com.example.marvelheroes.screens.character.ui.composePages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelheroes.R
import com.example.marvelheroes.repositories.network.api.models.characterModel.ResultCharacters
import com.example.marvelheroes.screens.character.model.Item
import com.example.marvelheroes.screens.character.viewmodel.ViewModelCharacter
import com.example.marvelheroes.screens.home.ui.utils.loadPicture
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBlue
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun characterMainScreen(item: List<ResultCharacters>, viewModelCharacter: ViewModelCharacter){
    Column(
        modifier = Modifier
            .background(darkBlue)
    ){
        Card(
            modifier = Modifier
                .height(350.dp)
                .background(darkBlue),
        ) {
            Box(
                modifier = Modifier
                    .background(darkBlue)
            ) {
                val url = item[0].thumbnail.path.replaceRange(
                    4,
                    4,
                    "s"
                ) + "/standard_amazing.${item[0].thumbnail.extension}"
                val image = loadPicture(
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
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(darkBlue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item[0].name,
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${item[0].description}...", color = Color.White, fontSize = 16.sp,textAlign = TextAlign.Justify)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Series: ${item[0].series.available} |",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default
                )
                Text(
                    text = " Stories: ${item[0].stories.available} |",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default
                )
                Text(
                    text = " Events: ${item[0].events.available}",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default
                )
            }
        }
        Column(

        ){
            Text("Stories", fontSize = 24.sp, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            //characterSeriesList()
            item[0].series.items.forEach { item ->
                item.resourceURI


            }
        }
    }
}

@Composable
fun characterSeriesList(itemsList: List<Item>){
    Column() {
        Text(
            text = "Comics",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        LazyColumn(modifier = Modifier.height(250.dp).background(darkBlue)) {
            items(itemsList) { card ->
                //characterSerieCard(card)
            }
        }
    }
}

/*
@Composable
fun characterSerieCard(card: Item){
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

    }*/