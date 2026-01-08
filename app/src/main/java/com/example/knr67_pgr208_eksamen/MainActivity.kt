package com.example.knr67_pgr208_eksamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.knr67_pgr208_eksamen.data.database.CharacterRepository
import com.example.knr67_pgr208_eksamen.navigation.AppNavigation
import com.example.knr67_pgr208_eksamen.screens.characterlist.CharacterListViewModel
import com.example.knr67_pgr208_eksamen.screens.createcharacter.CreateCharacterViewModel
import com.example.knr67_pgr208_eksamen.screens.randomcharacter.RandomCharacterViewModel
import com.example.knr67_pgr208_eksamen.screens.usercharacterlist.UserCharacterListViewModel
import com.example.knr67_pgr208_eksamen.ui.theme.Knr67_PGR208_EksamenTheme

class MainActivity : ComponentActivity() {

    private val _characterListViewModel: CharacterListViewModel by viewModels()
    private val _createCharacterViewModel: CreateCharacterViewModel by viewModels()
    private val _userCharacterViewModel: UserCharacterListViewModel by viewModels()
    private val _randomCharacterViewModel: RandomCharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CharacterRepository.initializeDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            Knr67_PGR208_EksamenTheme {
                AppNavigation(
                    _characterListViewModel,
                    _createCharacterViewModel,
                    _userCharacterViewModel,
                    _randomCharacterViewModel,
                )
            }
        }
    }
}


