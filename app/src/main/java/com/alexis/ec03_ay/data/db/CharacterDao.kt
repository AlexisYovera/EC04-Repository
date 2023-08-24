package com.alexis.ec03_ay.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alexis.ec03_ay.model.ChEntity
import com.alexis.ec03_ay.model.Character

@Dao
interface CharacterDao {
    @Insert
    suspend fun addCharacterToFavorite(charater: ChEntity)

    @Query("select * from character")
    suspend fun getFavoritos(): List<ChEntity>
}