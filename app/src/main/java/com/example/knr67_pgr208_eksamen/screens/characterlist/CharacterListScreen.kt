package com.example.knr67_pgr208_eksamen.screens.characterlist


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knr67_pgr208_eksamen.components.CharacterItem
import kotlinx.coroutines.delay

//Måtte bruke denne for å kunne endre på fargene på tekstfeltet.
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CharacterListScreen(characterListViewModel: CharacterListViewModel) {

    val characters by characterListViewModel.characterList.collectAsState();
    val searchedCharacter by characterListViewModel.searchedCharacter.collectAsState()
    val count by characterListViewModel.count.collectAsState()
    val pages by characterListViewModel.pages.collectAsState()
    val showKeyboard = LocalFocusManager.current
    val greenColor = Color(94, 193, 44)
    val listState = rememberLazyListState()

    var id by remember {
        mutableStateOf("")
    }
    var showAllCharacters by remember {
        mutableStateOf(true)
    }
    var loadingMessage by remember {
        mutableStateOf(false)
    }
    var page by remember {
        mutableIntStateOf(1)
    }

    LaunchedEffect(key1 = Unit) {
        loadingMessage = true
        delay(1000)
        characterListViewModel.getCharacters(page)
        characterListViewModel.getCount()
        characterListViewModel.getAmountOfPages()
        loadingMessage = false
    }

    //Sørger for at den scroller til toppen av siden når man går til neste side.
    LaunchedEffect(key1 = page) {
        listState.scrollToItem(0)

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(32, 32, 32)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Rick and Morty characters",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(67, 63, 70))
                .padding(16.dp),
            color = greenColor,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = id,
                onValueChange = { inputValue ->
                    if (inputValue.all { it.isDigit() }) {
                        id = inputValue
                    }
                },
                label = { Text(text = "Character id") },
                placeholder = { Text(text = "Id") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(172.dp)
                    .padding(4.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedIndicatorColor = greenColor,
                    focusedIndicatorColor = greenColor,
                )
            )

            Button(
                onClick = {
                    if (id.isNotEmpty()) {
                        showAllCharacters = false
                        characterListViewModel.getCharacterById(id.toInt())
                        showKeyboard.clearFocus()
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = greenColor)

            )
            {
                Text(text = "Search")
            }

            Button(
                onClick = {
                    showAllCharacters = true
                    id = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = greenColor),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            ) {
                Text(text = "Clear")

            }
        }

        Text(
            text = "- Tap the images for more information -",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(4.dp)
        )

        if (loadingMessage) {
            Text(
                text = "Loading characters...",
                color = Color.White
            )
        } else {
            Text(
                text = "Page $page of $pages",
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            if (showAllCharacters) {
                Column {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        items(characters) { character ->
                            CharacterItem(character = character)

                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (page > 1) {
                            Button(
                                onClick = {
                                    page--
                                    characterListViewModel.getCharacters(page)
                                },
                                modifier = Modifier
                                    .padding(4.dp),
                                colors = ButtonDefaults.buttonColors(greenColor),

                                ) {
                                Text(text = "Prev")
                            }
                        }
                        if (page < pages) {
                            Button(
                                onClick = {
                                    page++
                                    characterListViewModel.getCharacters(page)
                                },
                                modifier = Modifier
                                    .padding(4.dp),
                                colors = ButtonDefaults.buttonColors(greenColor),

                                ) {
                                Text(text = "Next")
                            }
                        }
                    }
                }
            } else {
                if (searchedCharacter == null) {
                    Column {
                        Text(
                            text = "No character found.",
                            color = Color.Gray
                        )
                        Text(
                            text = "Press a number between 1 and $count",
                            color = Color.Gray
                        )
                    }
                } else {
                    searchedCharacter?.let { character ->
                        CharacterItem(character = character)
                    }
                }
            }
        }
    }
}
