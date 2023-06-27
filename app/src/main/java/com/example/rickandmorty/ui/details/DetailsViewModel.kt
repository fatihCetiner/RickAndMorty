package com.example.rickandmorty.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.ApiService
import com.example.rickandmorty.data.RickMorty
import com.example.rickandmorty.repo.RickMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(
    private val apiService: ApiService,
    private val repository: RickMortyRepository
) : ViewModel() {

    val characterLiveData = MutableLiveData<RickMorty?>()

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getCharacter(id)
                }
                if (response.isSuccessful) {
                    val character = response.body()
                    characterLiveData.value = character
                }
            } catch (e: Exception) {
                characterLiveData.value = null
            }
        }
    }

    fun saveCharacter(character: RickMorty) {
        viewModelScope.launch {
            try {
                repository.insertCharacter(character)
            } catch (e: Exception) {
                // Not Saved
            }
        }
    }


}
