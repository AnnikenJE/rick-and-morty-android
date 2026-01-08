package com.example.knr67_pgr208_eksamen.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.knr67_pgr208_eksamen.screens.characterlist.CharacterListScreen
import com.example.knr67_pgr208_eksamen.screens.characterlist.CharacterListViewModel
import com.example.knr67_pgr208_eksamen.screens.createcharacter.CreateCharacterScreen
import com.example.knr67_pgr208_eksamen.screens.createcharacter.CreateCharacterViewModel
import com.example.knr67_pgr208_eksamen.screens.randomcharacter.RandomCharacterScreen
import com.example.knr67_pgr208_eksamen.screens.randomcharacter.RandomCharacterViewModel
import com.example.knr67_pgr208_eksamen.screens.usercharacterlist.UserCharacterListScreen
import com.example.knr67_pgr208_eksamen.screens.usercharacterlist.UserCharacterListViewModel
import kotlinx.serialization.Serializable

@Serializable
object CharactersList

@Serializable
object CreateCharacter

@Serializable
object UserCharacter

@Serializable
object RandomCharacter

@Composable
fun AppNavigation(
    characterListViewModel: CharacterListViewModel,
    createCharacterViewModel: CreateCharacterViewModel,
    userCharacterListViewModel: UserCharacterListViewModel,
    randomCharacterViewModel: RandomCharacterViewModel
) {
    val navController = rememberNavController()

    val itemColors = NavigationBarItemDefaults.colors(
        indicatorColor = Color(23, 44, 12),
        selectedIconColor = Color(94, 193, 44),
        selectedTextColor = Color(94, 193, 44),
        unselectedIconColor = Color(177, 188, 171),
        unselectedTextColor = Color(177, 188, 171)
    )

    var chosenScreenIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(modifier = Modifier.fillMaxWidth(), bottomBar = {
        NavigationBar(
            containerColor = Color(67, 63, 70),
        ) {

            NavigationBarItem(selected = chosenScreenIndex == 0,
                label = { Text(text = "Home") },
                colors = itemColors,
                onClick = {
                    chosenScreenIndex = 0
                    navController.navigate(CharactersList)
                },
                icon = {
                    if (chosenScreenIndex == 0) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "All characters",

                            )

                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "All characters"
                        )
                    }
                })

            NavigationBarItem(selected = chosenScreenIndex == 1,
                label = { Text(text = "Create") },
                colors = itemColors,
                onClick = {
                    chosenScreenIndex = 1
                    navController.navigate(CreateCharacter)
                },
                icon = {
                    if (chosenScreenIndex == 1) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Create character"
                        )

                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Create character"
                        )
                    }
                })

            NavigationBarItem(selected = chosenScreenIndex == 2,
                label = { Text(text = "Collection") },
                colors = itemColors,
                onClick = {
                    chosenScreenIndex = 2
                    navController.navigate(UserCharacter)
                },
                icon = {
                    if (chosenScreenIndex == 2) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "User made characters"
                        )

                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "User made characters"
                        )
                    }
                })

            NavigationBarItem(selected = chosenScreenIndex == 3,
                label = { Text(text = "Suprise me!") },
                colors = itemColors,
                onClick = {
                    chosenScreenIndex = 3
                    navController.navigate(RandomCharacter)
                },
                icon = {
                    if (chosenScreenIndex == 3) {
                        Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = "Random character"
                        )

                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Face,
                            contentDescription = "Random character"
                        )
                    }
                })
        }
    })
    { innerpadding ->

        Column(
            modifier = Modifier
                .padding(innerpadding)
        ) {
            NavHost(navController = navController, startDestination = CharactersList) {
                composable<CharactersList> {
                    CharacterListScreen(characterListViewModel)
                }
                composable<CreateCharacter> {
                    CreateCharacterScreen(createCharacterViewModel)
                }
                composable<UserCharacter> {
                    UserCharacterListScreen(userCharacterListViewModel)
                }
                composable<RandomCharacter> {
                    RandomCharacterScreen(randomCharacterViewModel)
                }
            }
        }
    }
}
