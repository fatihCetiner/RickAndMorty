package com.example.rickandmorty.room_db

import androidx.room.*
import com.example.rickandmorty.data.RickMorty

@Dao
interface RickMortyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: com.example.rickandmorty.data.RickMorty)

    @Delete
    suspend fun deleteCharacter(character: RickMorty)

    @Query("SELECT * FROM rickmorty WHERE id = :id")
    suspend fun getCharacterById(id: Int): RickMorty?

    @Query("SELECT * FROM rickmorty ORDER BY id DESC")
    suspend fun getAllCharacters(): List<RickMorty>
}