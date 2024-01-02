package com.example.rickandmorty.ui.signin

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel(){

    fun loginUser(email: String, password: String, callback: AuthRepository.AuthCallback) {
        authRepository.loginUser(email, password, callback)
    }

    fun getCurrentUser(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }
}