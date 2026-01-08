package com.example.knr67_pgr208_eksamen.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knr67_pgr208_eksamen.R
import com.example.knr67_pgr208_eksamen.data.dataclasses.MadeCharacter


@Composable
fun MadeCharacterItem(madeCharacter: MadeCharacter, onDelete: (() -> Unit)? = null) {

    val infoTextColor = Color.White
    val greenColor = Color(94, 193, 44)
    val redColor = Color(255, 50, 77)


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 60.dp, vertical = 16.dp)
                .border(
                    width = 2.dp,
                    color = greenColor,
                    shape = RoundedCornerShape(16.dp),
                )
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = madeCharacter.name,
                color = redColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )

            Image(
                painter = painterResource(id = R.drawable.pickle_rick),
                contentDescription = "Image of Picle Rick",
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = "Status: ${madeCharacter.status}",
                color = infoTextColor,
            )
            Text(
                text = "Species: ${madeCharacter.species}-pickle",
                color = infoTextColor,
            )
            Text(
                text = "Gender: ${madeCharacter.gender}",
                color = infoTextColor,
            )

            if (onDelete != null) {
                IconButton(onClick = {
                    onDelete()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete character",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
