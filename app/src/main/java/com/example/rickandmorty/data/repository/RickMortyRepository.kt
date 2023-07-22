package com.example.rickandmorty.data.repository

import com.example.rickandmorty.core.model.RickMorty
import com.example.rickandmorty.data.local.RickMortyDao
import javax.inject.Inject

class RickMortyRepository
@Inject
constructor(
    private val rickMortyDao: RickMortyDao
) {

    suspend fun insertCharacter(character: RickMorty) {
        rickMortyDao.insertCharacter(character)
    }

    suspend fun deleteCharacter(character: RickMorty) {
        rickMortyDao.deleteCharacter(character)
    }

    suspend fun getCharacterById(id: Int): RickMorty? {
        return rickMortyDao.getCharacterById(id)
    }

    suspend fun getAllCharacters(): List<RickMorty> {
        return rickMortyDao.getAllCharacters()
    }

}