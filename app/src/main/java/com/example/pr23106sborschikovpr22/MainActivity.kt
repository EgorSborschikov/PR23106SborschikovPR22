@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pr23106sborschikovpr22

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pr23106sborschikovpr22.ui.theme.PR23106SborschikovPR22Theme

class MainActivity : ComponentActivity() {
    private val viewModel: EmojiViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadEmojis()

        setContent {
            PR23106SborschikovPR22Theme {
                    MainContent(viewModel)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainContent(viewModel: EmojiViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    IconButton(onClick = { viewModel.loadEmojis() }) {
                        Icon(
                            Icons.Filled.Refresh,
                            contentDescription = "Reload Game"
                        )
                    }
                }
            )
        }
    ) {
        val cards: List<EmojiModel> by viewModel.getEmojis().observeAsState(listOf())
        CardsGrid(cards = cards, viewModel = viewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CardsGrid(cards: List<EmojiModel>, viewModel: EmojiViewModel) {
    LazyVerticalGrid(columns = GridCells.Fixed(4)) {
        items(cards.count()) { cardIndex ->
            CardItem(cards[cardIndex], viewModel)
        }
    }
}

@Composable
private fun CardItem(emoji: EmojiModel, viewModel: EmojiViewModel) {
    Box(
        modifier = Modifier
            .padding(all = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(150.dp)
                .background(
                    color = Color.Black.copy(alpha = if (emoji.isVisible) 0.4F else 0.0F),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    if (emoji.isVisible) {
                        viewModel.updateShowVisibleCard(emoji.id)
                    }
                }

        ) {
            if (emoji.isSelect) {
                Text(
                    text = emoji.char,
                    fontSize = 32.sp
                )
            }
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PR23106SborschikovPR22Theme {
        MainContent(viewModel: EmojiViewModel)
    }
}*/