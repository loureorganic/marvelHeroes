package com.example.marvelheroes.screens.character.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.marvelheroes.repositories.network.api.models.characterModel.ResultCharacters
import com.example.marvelheroes.screens.character.model.character.ResultCharacter

@Composable
fun AboutSection(character: ResultCharacters) {
    character.description?.let {
        Text(it)
    }
}