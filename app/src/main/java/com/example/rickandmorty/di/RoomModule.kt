package com.example.rickandmorty.di

import android.app.Application
import androidx.room.Room
import com.example.rickandmorty.data.repo.RickMortyRepository
import com.example.rickandmorty.room_db.RickMortyDao
import com.example.rickandmorty.room_db.RickMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): RickMortyDatabase {
        return Room.databaseBuilder(
            application, RickMortyDatabase::class.java,
            "RickMortyDB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: RickMortyDatabase) = database.rickMortyDao()

    @Provides
    @Singleton
    fun provideRickMortyRepository(rickMortyDao: RickMortyDao): RickMortyRepository {
        return RickMortyRepository(rickMortyDao)
    }
}