package com.example.marvelheroes.screens.home.ui.compose.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBlue

@Composable
fun notificationIcon() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(48.dp), // avoid the oval shape
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp), // avoid the little icon
        colors = ButtonDefaults.buttonColors(backgroundColor = darkBlue)
    ) {
        Icon(Icons.Default.Notifications, tint = Color.White, contentDescription = "content description")
    }
}
