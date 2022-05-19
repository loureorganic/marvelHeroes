package com.example.marvelheroes.screens.home.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.marvelheroes.screens.home.adapters.PhotoAdapter
import com.example.marvelheroes.screens.home.ui.ui.theme.MarvelHeroesTheme
import com.example.marvelheroes.screens.home.ui.utils.AutoSliding
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelHome: ViewModelHome

    lateinit var glide: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glide = PhotoAdapter(context = applicationContext)

        setContent {
            MarvelHeroesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AutoSliding()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        viewModelHome.getCharacters()
        viewModelHome.marvelList.observe(this) {
           it.data.results.slice(0..5).map { result ->
                val name = result.name
                val imageUrl =
                    result.thumbnail.path + "/standard_fantastic.${result.thumbnail.extension}"
                val description = result.description
           }
        }
    }
}