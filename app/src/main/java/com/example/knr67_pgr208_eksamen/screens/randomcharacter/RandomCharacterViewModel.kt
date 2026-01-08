package com.example.knr67_pgr208_eksamen.screens.randomcharacter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knr67_pgr208_eksamen.data.dataclasses.Character
import com.example.knr67_pgr208_eksamen.data.services.RMRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RandomCharacterViewModel : ViewModel() {

    private val _randomCharacter = MutableStateFlow<Character?>(null)
    private val _count = MutableStateFlow(0)
    val randomCharacter: StateFlow<Character?> = _randomCharacter.asStateFlow()
    val count = _count.asStateFlow()

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            try {
                _randomCharacter.value = RMRepository.getCharacterById(id)
            } catch (e: Exception) {
                Log.d(
                    "Error - getCharactersById",
                    "Problems getting characters by ID from API  - ${e.message}"
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
}
