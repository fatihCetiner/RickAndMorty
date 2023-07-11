package com.example.rickandmorty.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.api.ApiService
import com.example.rickandmorty.paging.RickMortyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val apiService: ApiService
) : ViewModel() {

    /*
    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    fun setLoadingState(isLoading: Boolean) {
        _loadingState.value = isLoading
    }

    val listData = Pager(PagingConfig(pageSize = 1)) {
        RickMortyPagingSource(apiService)
    }.flow
        .cachedIn(viewModelScope)
        .onStart {
            _loadingState.value = true
        }
        .onCompletion {
            _loadingState.value = false
        }
        .catch { error ->

        }

     */

    val listData = Pager(PagingConfig(pageSize = 1)) {
        RickMortyPagingSource(apiService)

    }.flow.cachedIn(viewModelScope)



}

