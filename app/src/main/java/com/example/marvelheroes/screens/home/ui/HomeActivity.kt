package com.example.marvelheroes.screens.home.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.R
import com.example.marvelheroes.screens.home.viewmodel.ViewModelHome
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelHome: ViewModelHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CardViewList()
        }
    }

    override fun onStart() {
        super.onStart()

    }

    @Composable
    fun MessageCard(msg: String = "Hi") {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Marvel Logo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Hello $msg",
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Sterling")
            }
        }
    }

    @Composable
    fun CardView() {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.Black,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .padding(16.dp)
            ) {
                Text("This is a card view")
            }
        }
    }

    @Composable
    fun CardViewList() {
        Row() {
            CardView()
            Spacer(modifier = Modifier.padding(8.dp))
            CardView()
            Spacer(modifier = Modifier.padding(8.dp))
            CardView()
            Spacer(modifier = Modifier.padding(8.dp))
            CardView()
        }
    }
}