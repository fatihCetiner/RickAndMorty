package com.example.rickandmorty.api

import com.example.rickandmorty.models.ResponseApi
import com.example.rickandmorty.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getAllCharacter(
        @Query("page") page:Int
    ): Response<ResponseApi>

}