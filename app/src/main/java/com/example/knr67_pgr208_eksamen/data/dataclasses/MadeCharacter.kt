package com.example.knr67_pgr208_eksamen.data.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MadeCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val gender: String,
    val species: String,
    val status: String
    )
