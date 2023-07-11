package com.example.rickandmorty.data.repo

import com.example.rickandmorty.data.model.RickMorty
import com.example.rickandmorty.room_db.RickMortyDao
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