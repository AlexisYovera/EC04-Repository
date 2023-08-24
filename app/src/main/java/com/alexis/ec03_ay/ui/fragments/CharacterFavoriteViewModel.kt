package com.alexis.ec03_ay.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alexis.ec03_ay.data.db.CharacterDataBase
import com.alexis.ec03_ay.data.repository.CharacterRepository
import com.alexis.ec03_ay.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterFavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val repository: CharacterRepository
    private var _favorites: MutableLiveData<List<Character>> = MutableLiveData()
    var favorites: LiveData<List<Character>> = _favorites

    init {
        val db = CharacterDataBase.getDataBase(application)
        repository = CharacterRepository(db)

    }

    fun getFavorites(){
        viewModelScope.launch(Dispatchers.IO){
            val data = repository.getFavorites()
            _favorites.postValue(data)
        }
    }
}