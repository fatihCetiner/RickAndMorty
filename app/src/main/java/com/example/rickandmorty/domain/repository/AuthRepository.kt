package com.example.rickandmorty.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    interface AuthCallback {
        fun onSuccess(user: FirebaseUser?)
        fun onFailure(errorMessage: String)
    }

    fun loginUser(email: String, password: String, callback: AuthCallback)
    fun registerUser(email: String, password: String, callback: AuthCallback)
    fun logoutUser(callback: AuthCallback)
    fun getCurrentUser(): FirebaseUser?
}