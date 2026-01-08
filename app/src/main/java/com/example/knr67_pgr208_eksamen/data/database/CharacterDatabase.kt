package com.example.knr67_pgr208_eksamen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.knr67_pgr208_eksamen.data.dataclasses.MadeCharacter


@Database(
    entities = [MadeCharacter::class],
    version = 1,
    exportSchema = false
)

abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao

}