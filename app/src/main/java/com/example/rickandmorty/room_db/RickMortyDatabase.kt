package com.example.rickandmorty.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rickandmorty.data.RickMorty
import com.example.rickandmorty.utils.StringListConverter

@Database(entities = [RickMorty::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class RickMortyDatabase : RoomDatabase() {
    abstract fun rickMortyDao(): RickMortyDao
}