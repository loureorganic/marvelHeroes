package com.example.marvelheroes.screens.search.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.marvelheroes.screens.home.ui.utils.loadPicture
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBlue
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@Composable
fun characterCard(item: List<ResultCharacters>, copyrightData: String) {
    Column(
        modifier = Modifier
            .height(520.dp)
            .padding(8.dp)
            .background(darkBlue)
    ) {
        Card(
            modifier = Modifier
                .height(300.dp)
                .background(darkBlue)
        ) {
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
        Card(
            modifier = Modifier
                .height(220.dp)
                .padding(8.dp)
                .background(darkBlue)
        ) {
            Column(
                modifier = Modifier
                    .background(darkBlue),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item[0].name,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item[0].description, color = Color.White, maxLines = 2)
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
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = copyrightData,
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AssistChip(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        onClick = { /*TODO*/ },
                        colors = AssistChipDefaults.assistChipColors(leadingIconContentColor = Color.White),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = null,
                                tint = Color.White
                            )
                        },
                        label = {
                            Text(
                                text = "View more",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(14.dp, 0.dp)
                            )
                        }
                    )
                    AssistChip(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        onClick = { /*TODO*/ },
                        colors = AssistChipDefaults.assistChipColors(leadingIconContentColor = Color.White),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = null,
                                tint = Color.White
                            )
                        },
                        label = { Text(text = "Mark as favorite", color = Color.White) }
                    )
                }

            }
        }
    }
}