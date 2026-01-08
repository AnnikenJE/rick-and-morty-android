package com.example.knr67_pgr208_eksamen.screens.randomcharacter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knr67_pgr208_eksamen.R
import com.example.knr67_pgr208_eksamen.components.CharacterItem

@Composable
fun RandomCharacterScreen(randomCharacterViewModel: RandomCharacterViewModel) {

    val randomCharacter by randomCharacterViewModel.randomCharacter.collectAsState()
    val count by randomCharacterViewModel.count.collectAsState()
    val greenColor = Color(94, 193, 44)

    var hasClickedButton by remember {
        mutableStateOf(false)
    }
    var randomId by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = Unit) {
        randomCharacterViewModel.getCount()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(32, 32, 32)),

        ) {

        Text(
            text = "What Rick and Morty character are you?",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(67, 63, 70))
                .padding(16.dp),
            color = Color(94, 193, 41),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if (!hasClickedButton) {
                    Button(onClick = {
                        randomId = (1..(count)).random()
                        hasClickedButton = true
                        randomCharacterViewModel.getCharacterById(randomId)
                    }, colors = ButtonDefaults.buttonColors(containerColor = greenColor)) {
                        Text(
                            text = "CLICK ME",
                            fontSize = 32.sp,
                            color = Color.Black
                        )
                        Image(
                            painter = painterResource(id = R.drawable.pickle_rick),
                            contentDescription = "Pickle Rick Image"
                        )
                    }
                } else {
                    Text(
                        text = "You are:",
                        color = greenColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    randomCharacter.let { character ->
                        if (character != null) {
                            CharacterItem(character = character)
                        }
                    }

                    Button(
                        modifier = Modifier.padding(top = 32.dp),
                        onClick = {
                            randomId = (1..(count)).random()
                            randomCharacterViewModel.getCharacterById(randomId)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = greenColor),
                    ) {
                        Text(text = "Not happy?")

                    }
                }
            }
        }
    }
}