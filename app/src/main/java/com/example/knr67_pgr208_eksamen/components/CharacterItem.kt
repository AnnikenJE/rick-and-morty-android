package com.example.knr67_pgr208_eksamen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.knr67_pgr208_eksamen.data.dataclasses.Character

@Composable
fun CharacterItem(character: Character) {

    var showCharacterInfo by remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(top = 24.dp)
            .clickable { showCharacterInfo = !showCharacterInfo }
    ) {

        Text(
            text = " ${character.id} - ${character.name}",
            fontWeight = FontWeight.Bold,
            color = Color(255, 50, 77),
            fontSize = 24.sp
        )

        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(160.dp)
        )

        AnimatedVisibility(visible = showCharacterInfo,
            enter = slideInVertically { it },
            exit = slideOutVertically { it })
        {

            Column {
                if (character.status != "unknown") {
                    Text(
                        text = character.status,
                        color =
                        if (character.status == "Dead") {
                            Color.Red
                        } else {
                            Color.Green
                        }
                    )
                }

                Text(
                    text = "Species - ${character.species}",
                    color = Color.Gray
                )

                Text(
                    text = "Gender - ${character.gender}",
                    color = Color.Gray
                )
            }
        }
    }
}