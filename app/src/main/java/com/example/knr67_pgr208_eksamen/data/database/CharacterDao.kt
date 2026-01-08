package com.example.knr67_pgr208_eksamen.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.knr67_pgr208_eksamen.data.dataclasses.MadeCharacter

@Dao
interface CharacterDao {

    @Query("SELECT * FROM MadeCharacter")
    suspend fun getMadeCharacters() : List<MadeCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMadeCharacter(madeCharacter: MadeCharacter) : Long

    @Delete
    suspend fun deleteMadeCharacter(madeCharacter: MadeCharacter) : Int

}