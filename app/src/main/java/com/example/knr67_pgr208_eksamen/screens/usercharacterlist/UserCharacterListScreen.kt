package com.example.knr67_pgr208_eksamen.screens.usercharacterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knr67_pgr208_eksamen.components.MadeCharacterItem
import kotlinx.coroutines.delay


@Composable
fun UserCharacterListScreen(userCharacterListViewModel: UserCharacterListViewModel) {

    val madeCharacters = userCharacterListViewModel.characters.collectAsState()

    var loadingMessage by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        loadingMessage = true
        delay(1000)
        userCharacterListViewModel.getCharacters()
        loadingMessage = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(32, 32, 32)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Characters that you have made",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(67, 63, 70))
                .padding(16.dp),
            color = Color(94, 193, 41),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )

        if (loadingMessage) {
            Text(
                text = "Loading characters...",
                color = Color.White
            )
        } else {
            LazyColumn {
                items(madeCharacters.value) { madeCharacter ->
                    MadeCharacterItem(madeCharacter = madeCharacter,
                        onDelete = { userCharacterListViewModel.deleteCharacter(madeCharacter) })

                }
            }
        }
    }
}