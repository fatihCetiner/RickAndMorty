package com.example.rickandmorty.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.api.ApiService
import com.example.rickandmorty.models.RickMorty
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(
    private val apiService: ApiService
) :ViewModel(){

    val characterLiveData = MutableLiveData<RickMorty>()

     fun getCharacter(id :Int){

         apiService.getCharacter(id).enqueue(object : Callback<RickMorty> {

            override fun onResponse(
                call: Call<RickMorty>,
                response: Response<RickMorty>
            ) {
                if (response.isSuccessful) {
                    characterLiveData.value = response.body()

                }else{

                }
            }

            override fun onFailure(call: Call<RickMorty>, t: Throwable) {

            }
        })
    }
}