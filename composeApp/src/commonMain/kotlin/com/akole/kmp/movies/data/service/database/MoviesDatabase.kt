package com.akole.kmp.movies.data.service.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akole.kmp.movies.data.dto.database.MovieEntity

const val DATABASE_NAME = "movies.db"

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDatabase: RoomDatabase(), DB {
    abstract fun moviesDao(): MoviesDao
    override fun clearAllTables() {}
}

interface DB {
    fun clearAllTables() {}
}