package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.source.remote.dto.RickMorty
import com.example.rickandmorty.data.source.local.RickMortyDao
import com.example.rickandmorty.domain.repository.RickMortyRepository
import javax.inject.Inject

class RickMortyRepositoryImpl
@Inject
constructor(
    private val rickMortyDao: RickMortyDao
):RickMortyRepository {

    override suspend fun insertCharacter(character: RickMorty) {
        rickMortyDao.insertCharacter(character)
    }

    override suspend fun deleteCharacter(character: RickMorty) {
        rickMortyDao.deleteCharacter(character)
    }

    override suspend fun getCharacterById(id: Int): RickMorty? {
        return rickMortyDao.getCharacterById(id)
    }

    override suspend fun getAllCharacters(): List<RickMorty> {
        return rickMortyDao.getAllCharacters()
    }

}