package com.example.rickandmorty.ui.signup

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun registerUser(email: String, password: String, callback: AuthRepository.AuthCallback) {
        authRepository.registerUser(email, password, callback)
    }

    fun getCurrentUser(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }
}