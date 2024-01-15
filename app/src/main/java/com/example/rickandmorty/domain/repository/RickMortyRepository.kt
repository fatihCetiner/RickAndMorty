package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.data.source.remote.dto.RickMorty


interface RickMortyRepository {

    suspend fun insertCharacter(character: RickMorty)

    suspend fun deleteCharacter(character: RickMorty)

    suspend fun getCharacterById(id: Int): RickMorty?

    suspend fun getAllCharacters(): List<RickMorty>
}