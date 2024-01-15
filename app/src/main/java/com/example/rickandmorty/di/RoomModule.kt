package com.example.rickandmorty.di

import android.app.Application
import androidx.room.Room
import com.example.rickandmorty.data.repository.RickMortyRepositoryImpl
import com.example.rickandmorty.data.source.local.RickMortyDao
import com.example.rickandmorty.data.source.local.RickMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @[Singleton Provides]
    fun provideRoomDatabase(application: Application): RickMortyDatabase {
        return Room.databaseBuilder(
            application, RickMortyDatabase::class.java,
            "RickMortyDB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @[Singleton Provides]
    fun provideDao(database: RickMortyDatabase) = database.rickMortyDao()

    @[Provides Singleton]
    fun provideRickMortyRepository(rickMortyDao: RickMortyDao): RickMortyRepositoryImpl {
        return RickMortyRepositoryImpl(rickMortyDao)
    }
}