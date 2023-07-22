package com.example.rickandmorty.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.utils.RickMortyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
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

