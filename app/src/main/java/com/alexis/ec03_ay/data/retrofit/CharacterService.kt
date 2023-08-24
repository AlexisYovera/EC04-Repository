package com.alexis.ec03_ay.data.retrofit

import com.alexis.ec03_ay.data.response.ListCharacterResponse
import retrofit2.http.GET

interface CharacterService {
    @GET("character")
    suspend fun getAllCharacters(): ListCharacterResponse
}