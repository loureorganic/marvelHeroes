package com.example.marvelheroes.screens.character.ui.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.marvelheroes.R

// Set of Material typography styles to start with

val fonts = FontFamily(
    Font(R.font.poppins_extrabold),
    Font(R.font.poppins_extrabold_italic),
    Font(R.font.poppins_bold),
    Font(R.font.poppins_bold_italic),
    Font(R.font.poppins_semibold),
    Font(R.font.poppins_semibold_italic),
    Font(R.font.poppins_black),
    Font(R.font.poppins_black_italic),
    Font(R.font.poppins_medium),
    Font(R.font.poppins_medium_italic),
    Font(R.font.poppins_light),
    Font(R.font.poppins_light_italic),
    Font(R.font.poppins_regular),
    Font(R.font.poppins_thin),
    Font(R.font.poppins_thin_italic)
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)