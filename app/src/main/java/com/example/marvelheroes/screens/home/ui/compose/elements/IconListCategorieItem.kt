package com.example.marvelheroes.screens.home.ui.compose.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.screens.search.ui.ui.theme.darkRed

@Composable
fun IconListCategoriesItem(iconName: Painter) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(62.dp).clip(RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp)),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = darkRed)
    ) {
        Icon(iconName, tint = Color.White, contentDescription = "content description", modifier =  Modifier.size(40.dp))
    }
}
