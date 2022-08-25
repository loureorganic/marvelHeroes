package com.example.marvelheroes.screens.home.ui.compose.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.marvelheroes.R
import com.example.marvelheroes.screens.home.ui.compose.elements.IconListCategoriesItem

@Composable
fun IconListCategoriesComponent() {
    IconListCategoriesItem(painterResource(R.drawable.icon_characters))
    IconListCategoriesItem(painterResource(R.drawable.icon_comics))
    IconListCategoriesItem(painterResource(R.drawable.icon_series))
    IconListCategoriesItem(painterResource(R.drawable.icon_stories))
}
