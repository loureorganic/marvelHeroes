package com.example.marvelheroes.screens.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.screens.character.services.ServicesCharacter
import com.example.marvelheroes.screens.home.repository.RepositoryHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelCharacter {
    fun getCharacterSeries()
}

@HiltViewModel
class CharacterViewModel @Inject constructor(private val services: ServicesCharacter) : ViewModel(), ViewModelCharacter {
    override fun getCharacterSeries() {
        viewModelScope.launch(Dispatchers.IO) {
         services.getCharacterSeries()
        }
    }
}