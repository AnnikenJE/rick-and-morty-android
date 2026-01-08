package com.example.knr67_pgr208_eksamen.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.knr67_pgr208_eksamen.data.dataclasses.MadeCharacter
import java.sql.SQLException

object CharacterRepository {

    private lateinit var _characterDatabase: CharacterDatabase
    private val _characterDao by lazy { _characterDatabase.characterDao() }

    fun initializeDatabase(context: Context) {
        _characterDatabase = Room.databaseBuilder(
            context = context,
            klass = CharacterDatabase::class.java,
            name = "character-database"
        ).build()
    }

    suspend fun getMadeCharacters(): List<MadeCharacter> {
        try {
            return _characterDao.getMadeCharacters()
        } catch (e: SQLException) {
            Log.d("SQLException - getMadeCharacters", "Problems getting characters from the database - ${e.message}" )
            return emptyList()
        } catch (e: Exception) {
            Log.d("Unknown error - getMadeCharacters", "Problems getting characters from the database - ${e.message}" )
            return emptyList()
        }
    }

    suspend fun insertCharacter(madeCharacter: MadeCharacter): Long {
        try {
            return _characterDao.insertMadeCharacter(madeCharacter = madeCharacter)
        } catch (e: SQLException) {
            Log.d("SQLException - insertCharacter", "Problems inserting characters into the database - ${e.message}" )
            return -1L
        } catch (e: Exception) {
            Log.d("Unknown error - insertCharacter", "Problems inserting characters into the database - ${e.message}" )
            return -1L
        }
    }

    suspend fun deleteCharacter(madeCharacter: MadeCharacter): Int {
        try {
            return _characterDao.deleteMadeCharacter(madeCharacter)

        } catch (e: SQLException) {
            Log.d("SQLException - deleteCharacter", "Problems deleting character from the database - ${e.message}" )
            return 0
        } catch (e: Exception) {
            Log.d("Unknown - deleteCharacter", "Problems deleting characters from the database - ${e.message}" )
            return 0
        }
    }
}