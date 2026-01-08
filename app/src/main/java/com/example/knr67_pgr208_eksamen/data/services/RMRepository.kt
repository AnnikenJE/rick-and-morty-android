package com.example.knr67_pgr208_eksamen.data.services

import android.util.Log
import com.example.knr67_pgr208_eksamen.data.dataclasses.Character
import com.example.knr67_pgr208_eksamen.data.dataclasses.Info
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RMRepository {

    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    private val _characterService = _retrofit.create(RMService::class.java)

    suspend fun getCharactersByPage(page: Int = 1): List<Character> {
        return try {
            val response = _characterService.getCharactersByPage(page)
            if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("Could not get characters from API", e.toString())
            emptyList()
        }
    }

    suspend fun getInfo(): Info? {
        return try {
            val response = _characterService.getCharactersByPage()
            if (response.isSuccessful) {
                response.body()?.info
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d("Could not get info", e.toString())
            null
        }
    }

    suspend fun getCharacterById(id: Int): Character? {
        return try {
            val response = _characterService.getCharacterById(id)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d("Could not get character by ID", e.toString())
            null
        }
    }
}