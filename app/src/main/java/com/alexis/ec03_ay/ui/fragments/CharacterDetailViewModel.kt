package com.alexis.ec03_ay.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alexis.ec03_ay.data.db.CharacterDataBase
import com.alexis.ec03_ay.data.repository.CharacterRepository
import com.alexis.ec03_ay.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository: CharacterRepository
    init {
        val db = CharacterDataBase.getDataBase(application)
        repository = CharacterRepository(db)

    }

    fun addFavorites(character: Character){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCharacterToFavorites(character)
        }
    }
}