package com.example.rickandmorty.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.api.ApiService
import com.example.rickandmorty.paging.RickMortyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val apiService: ApiService
): ViewModel(){

    val listData = Pager(PagingConfig(pageSize = 1)) {
        RickMortyPagingSource(apiService)

    }.flow.cachedIn(viewModelScope)




}

