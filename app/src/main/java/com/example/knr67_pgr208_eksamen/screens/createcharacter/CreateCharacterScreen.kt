package com.example.knr67_pgr208_eksamen.screens.createcharacter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knr67_pgr208_eksamen.R
import com.example.knr67_pgr208_eksamen.data.dataclasses.MadeCharacter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCharacterScreen(createCharacterViewModel: CreateCharacterViewModel) {

    val showKeyboard = LocalFocusManager.current
    val greenColor = Color(94, 193, 44)
    val textFieldColors = TextFieldDefaults.textFieldColors(
        unfocusedLabelColor = Color.Black,
        focusedLabelColor = Color.Black,
        unfocusedIndicatorColor = greenColor,
        focusedIndicatorColor = greenColor,
    )

    var name by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }
    var species by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf("")
    }
    var lastCharacterName by remember {
        mutableStateOf("")
    }
    var updateInfo by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(32, 32, 32)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Create a character!",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(67, 63, 70))
                .padding(16.dp),
            color = Color(94, 193, 41),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        TextField(
            value = name,
            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
            onValueChange = { name = it },
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Character name") },
            colors = textFieldColors
        )

        TextField(
            value = status,
            modifier = Modifier.padding(4.dp),
            onValueChange = { status = it },
            label = { Text(text = "Status") },
            placeholder = { Text(text = "Dead or alive?") },
            colors = textFieldColors
        )

        TextField(
            value = gender,
            modifier = Modifier.padding(4.dp),
            onValueChange = { gender = it },
            label = { Text(text = "Gender") },
            placeholder = { Text(text = "Character gender") },
            colors = textFieldColors
        )

        TextField(
            value = species,
            modifier = Modifier.padding(4.dp),
            onValueChange = { species = it },
            label = { Text(text = "Species") },
            placeholder = { Text(text = "Character species") },
            colors = textFieldColors
        )

        Button(
            onClick = {
                if (name != "") {
                    updateInfo = true
                    showKeyboard.clearFocus()
                    createCharacterViewModel.insertCharacter(
                        MadeCharacter(
                            name = name,
                            gender = gender,
                            species = species,
                            status = status,
                        )
                    )
                    lastCharacterName = name
                    name = ""
                    gender = ""
                    species = ""
                    status = ""
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(94, 193, 44))
        )
        {
            Text(text = "Add Character")
        }

        if (!updateInfo) {
            Text(
                text = "",
                color = Color.White
            )

        } else {
            Text(
                text = "$lastCharacterName got saved!",
                color = Color.White
            )
        }

        Image(
            painter = painterResource(id = R.drawable.pickle_rick),
            contentDescription = "Pickle Rick Image",
            Modifier
                .size(160.dp)
                .padding(
                    32.dp
                )
        )
    }
}

