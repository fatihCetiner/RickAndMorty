package com.example.rickandmorty.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.remote.dto.RickMorty
import com.example.rickandmorty.data.repository.RickMortyRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject
constructor(
    private val repository: RickMortyRepositoryImpl
) : ViewModel() {


    private val _characterList = MutableLiveData<List<RickMorty>>()
    val characterList: LiveData<List<RickMorty>> = _characterList

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    fun getAllCharacters() {
        viewModelScope.launch {
            val characters = repository.getAllCharacters()
            if (characters.isNotEmpty()) {
                _characterList.value = characters
                _isEmpty.value = false
            } else {
                _isEmpty.value = true
            }
        }
    }

    fun deleteCharacter(character: RickMorty) {
        viewModelScope.launch {
            repository.deleteCharacter(character)
        }
    }

}