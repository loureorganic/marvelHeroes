package com.example.marvelheroes.screens.home.ui.compose

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.R
import com.example.marvelheroes.screens.home.ui.HomeActivity
import com.example.marvelheroes.screens.home.ui.utils.SearchWidgetState
import com.example.marvelheroes.screens.search.ui.SearchActivity
import com.example.marvelheroes.screens.search.ui.ui.theme.darkBlue
import okhttp3.internal.wait


@Composable
fun MarvelHeaderApp(onSearchClicked: () -> Unit) {
    TopAppBar(backgroundColor = darkBlue) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.marvel_heroes_logo_white),
                contentDescription = "Marvel Logo",
                modifier = Modifier
                    .padding(8.dp)
                    .size(120.dp)
            )

            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    context: Context,
    function: (a: Intent) -> (Unit)
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), elevation = AppBarDefaults.TopAppBarElevation, color = darkBlue
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    text = "Search here...",
                    color = Color.White,
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontFamily = MaterialTheme.typography.subtitle1.fontFamily,
                color = Color.White
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = {}, modifier = Modifier.alpha(ContentAlpha.medium)) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                    } else {
                        onCloseClicked()
                        function(Intent(context, HomeActivity::class.java))
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    context: Context,
    function: (a: Intent) -> (Unit)
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            MarvelHeaderApp(
                onSearchClicked = onSearchTriggered
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked,
                context = context,
                function = function
            )
        }
    }
}