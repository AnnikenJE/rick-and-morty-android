package com.example.knr67_pgr208_eksamen.screens.usercharacterlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knr67_pgr208_eksamen.data.database.CharacterRepository
import com.example.knr67_pgr208_eksamen.data.dataclasses.MadeCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.SQLException

class UserCharacterListViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<MadeCharacter>>(emptyList())
    val characters = _characters.asStateFlow()

    fun getCharacters() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _characters.value = CharacterRepository.getMadeCharacters()
            }
        } catch (e: SQLException) {
            Log.d(
                "SQLException - getCharacters",
                "Problems getting characters from the database  - ${e.message}"
            )
        } catch (e: Exception) {
            Log.d(
                "Error - getCharacters",
                "Problems getting characters from the database  - ${e.message}"
            )
        }
    }

    fun deleteCharacter(character: MadeCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            val deletedCharacterNumber = CharacterRepository.deleteCharacter(character)
            try {
                if (deletedCharacterNumber == 1) {
                    val currentList = _characters.value
                    val afterDeleteList = currentList.filter { it != character }
                    _characters.value = afterDeleteList
                }
            } catch (e: Exception) {
                Log.d("Can't delete character - UserCharacterListViewModel", e.toString())
            }
        }
    }
}