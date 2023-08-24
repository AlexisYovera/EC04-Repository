package com.alexis.ec03_ay.data.repository

import com.alexis.ec03_ay.data.CharacterServiceResult
import com.alexis.ec03_ay.data.db.CharacterDao
import com.alexis.ec03_ay.data.db.CharacterDataBase
import com.alexis.ec03_ay.data.response.ListCharacterResponse
import com.alexis.ec03_ay.data.retrofit.RetrofitHelper
import com.alexis.ec03_ay.model.ChEntity
import com.alexis.ec03_ay.model.Character
import com.alexis.ec03_ay.model.toChEntity
import com.alexis.ec03_ay.model.toCharacter

class CharacterRepository(val db: CharacterDataBase ?= null) {
    private val dao: CharacterDao? = db?.characterDao()
    suspend fun getCharacters(): CharacterServiceResult<ListCharacterResponse>{
        return try {
            val response = RetrofitHelper.characterService.getAllCharacters()
            CharacterServiceResult.Succes(response)
        }catch (e: Exception){
            CharacterServiceResult.Error(e)
        }

    }
    suspend fun getFavorites(): List<Character> {
        dao?.let {
            val data = dao.getFavoritos()
            val characters: MutableList<Character> = mutableListOf()
            for(ChEntity in data){
                characters.add(ChEntity.toCharacter())
            }
            return characters

        } ?: kotlin.run {
            return listOf()
        }

    }

    suspend fun addCharacterToFavorites(character: Character){
        dao?.let {
            dao.addCharacterToFavorite(character.toChEntity())
        }
    }
}