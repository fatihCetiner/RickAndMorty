package com.example.rickandmorty.data.local

import androidx.room.*
import com.example.rickandmorty.core.model.RickMorty

@Dao
interface RickMortyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: RickMorty)

    @Delete
    suspend fun deleteCharacter(character: RickMorty)

    @Query("SELECT * FROM rickmorty WHERE id = :id")
    suspend fun getCharacterById(id: Int): RickMorty?

    @Query("SELECT * FROM rickmorty ORDER BY id DESC")
    suspend fun getAllCharacters(): List<RickMorty>
}