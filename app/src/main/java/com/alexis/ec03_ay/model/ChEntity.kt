package com.alexis.ec03_ay.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class ChEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    var isFavorite: Boolean = false
)

fun ChEntity.toCharacter(): Character{
    return Character(
        id,name,status,species,gender,isFavorite
    )
}
