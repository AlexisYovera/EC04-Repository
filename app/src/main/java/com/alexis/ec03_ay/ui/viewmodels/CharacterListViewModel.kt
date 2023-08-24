package com.alexis.ec03_ay.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexis.ec03_ay.data.CharacterServiceResult
import com.alexis.ec03_ay.data.repository.CharacterRepository
import com.alexis.ec03_ay.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel: ViewModel() {
    private val _characters: MutableLiveData<List<Character>> = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters
    val repository = CharacterRepository()




    fun getCharactersFromService(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getCharacters()
            when(response){
                is CharacterServiceResult.Succes -> {
                    _characters.postValue(response.data.results)
                }
                is CharacterServiceResult.Error -> {
                    //
                }
            }
        }
    }
}