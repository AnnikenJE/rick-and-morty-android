package com.example.knr67_pgr208_eksamen.screens.createcharacter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knr67_pgr208_eksamen.data.database.CharacterRepository
import com.example.knr67_pgr208_eksamen.data.dataclasses.MadeCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.sql.SQLException

class CreateCharacterViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<MadeCharacter>>(emptyList())

    fun insertCharacter(character: MadeCharacter) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val newCharacterId = CharacterRepository.insertCharacter(character)
                if (newCharacterId != -1L) {
                    val newCharacter = character.copy(id = newCharacterId.toInt())
                    _characters.value += newCharacter
                }
            }
        } catch (e: SQLException) {
            Log.d(
                "SQLException - insertCharacters",
                "Problems inserting character into the database - ${e.message}"
            )
        } catch (e: Exception) {
            Log.d(
                "Error - insertCharacters",
                "Problems inserting character into the database - ${e.message}"
            )
        }
    }
}