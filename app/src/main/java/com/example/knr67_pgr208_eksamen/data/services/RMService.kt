package com.example.knr67_pgr208_eksamen.data.services


import com.example.knr67_pgr208_eksamen.data.dataclasses.Character
import com.example.knr67_pgr208_eksamen.data.dataclasses.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RMService {

    @GET("character")
    suspend fun getCharactersByPage(
        @Query("page") page: Int = 1
    ): Response<CharacterList>

    @GET("character/{id}/")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ) : Response<Character>

}
