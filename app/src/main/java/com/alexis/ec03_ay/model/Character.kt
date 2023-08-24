package com.alexis.ec03_ay.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    var isFavorite:Boolean = false
):Parcelable

fun Character.toChEntity(): ChEntity{
    return ChEntity(
        id,name,status,species,gender,isFavorite
    )
}
