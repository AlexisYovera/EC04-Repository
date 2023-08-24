package com.alexis.ec03_ay.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val characterService: CharacterService = retrofit.create(CharacterService::class.java)
}

//https://run.mocky.io/v3/01f62514-c17a-4a85-bac9-d633ea44c511