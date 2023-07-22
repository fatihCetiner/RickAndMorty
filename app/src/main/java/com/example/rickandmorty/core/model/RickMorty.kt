package com.example.rickandmorty.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RickMorty(
    val created: String,
    val episode: List<String>,
    val gender: String,
    @PrimaryKey val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)