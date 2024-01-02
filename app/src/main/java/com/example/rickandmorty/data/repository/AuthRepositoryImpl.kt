package com.example.rickandmorty.data.repository

import com.example.rickandmorty.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl  @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override fun loginUser(email: String, password: String, callback: AuthRepository.AuthCallback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.onSuccess(firebaseAuth.currentUser)
                } else {
                    callback.onFailure(task.exception?.message ?: "Giriş yapılamadı.")
                }
            }
    }

    override fun registerUser(email: String, password: String, callback: AuthRepository.AuthCallback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.onSuccess(firebaseAuth.currentUser)
                } else {
                    callback.onFailure(task.exception?.message ?: "Kayıt yapılamadı.")
                }
            }
    }

    override fun logoutUser(callback: AuthRepository.AuthCallback) {
        firebaseAuth.signOut()
        callback.onSuccess(null)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}