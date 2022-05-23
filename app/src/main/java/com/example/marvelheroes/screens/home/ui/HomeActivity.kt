package com.example.marvelheroes.screens.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.R
import com.example.marvelheroes.databinding.ActivityHomeBinding
import com.example.marvelheroes.screens.home.ui.utils.PhotoAdapter
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var viewModelHome: ViewModelHome

    lateinit var photoAdapter: PhotoAdapter

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        photoAdapter = PhotoAdapter(this)
    }

    override fun onStart() {
        super.onStart()

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.adapter = photoAdapter

        listCharactersAtPhotoAdapter()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listCharactersAtPhotoAdapter() {
        viewModelHome.getCharacters()

        viewModelHome.marvelList.observe(this) { result ->
            if (result.isNotEmpty()) {
                photoAdapter.setDataList(result)
                photoAdapter.notifyDataSetChanged()

                result.map { a ->
                    Log.i("A", "RESULTADO ${a.name}")
                }
            }
        }
    }
}