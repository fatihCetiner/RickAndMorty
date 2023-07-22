package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.core.model.RickMorty
import com.example.rickandmorty.data.local.RickMortyDao
import com.example.rickandmorty.utils.StringListConverter

@Database(entities = [RickMorty::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class RickMortyDatabase : RoomDatabase() {
    abstract fun rickMortyDao(): RickMortyDao
}