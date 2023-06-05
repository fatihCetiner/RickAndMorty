package com.example.rickandmorty.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.ApiService
import com.example.rickandmorty.data.RickMorty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(
    private val apiService: ApiService
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

}
