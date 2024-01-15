package com.example.rickandmorty.data.source.remote

import com.example.rickandmorty.data.source.remote.dto.ResponseApi
import com.example.rickandmorty.data.source.remote.dto.RickMorty
import com.example.rickandmorty.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getAllCharacter(
        @Query("page") page: Int
    ): Response<ResponseApi>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<RickMorty>

}
