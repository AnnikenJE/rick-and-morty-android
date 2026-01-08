package com.example.knr67_pgr208_eksamen.screens.characterlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knr67_pgr208_eksamen.data.dataclasses.Character
import com.example.knr67_pgr208_eksamen.data.services.RMRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    private val _character = MutableStateFlow<Character?>(null)
    private val _count = MutableStateFlow(0)
    private val _pages = MutableStateFlow(0)
    val characterList = _characters.asStateFlow()
    val searchedCharacter = _character.asStateFlow()
    val count = _count.asStateFlow()
    val pages = _pages.asStateFlow()

    fun getCharacters(page: Int) {
        viewModelScope.launch {
            try {
                _characters.value = RMRepository.getCharactersByPage(page)
            } catch (e: Exception) {
                Log.d(
                    "Error - getCharacters",
                    "Problems getting characters from the API - ${e.message}"
                )
            }
        }
    }

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            try {
                _character.value = RMRepository.getCharacterById(id)
            } catch (e: Exception) {
                Log.d(
                    "Error - getCharactersById",
                    "Problems getting characters from the API - ${e.message}"
                )
            }
        }
    }

    fun getCount() {
        viewModelScope.launch {
            try {
                _count.value = RMRepository.getInfo()?.count ?: 0
            } catch (e: Exception) {
                Log.d(
                    "Error - getCount",
                    "Problems getting info from the API - ${e.message}"
                )
            }
        }
    }

    fun getAmountOfPages() {
        viewModelScope.launch {
            try {
                _pages.value = RMRepository.getInfo()?.pages ?: 0
            } catch (e: Exception) {
                Log.d(
                    "Error - getAmountOfPages",
                    "Problems getting info from the API - ${e.message}"
                )
            }
        }
    }
}