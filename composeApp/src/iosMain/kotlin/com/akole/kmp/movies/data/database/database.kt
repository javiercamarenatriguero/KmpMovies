package com.akole.kmp.movies.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.akole.kmp.movies.data.service.database.DATABASE_NAME
import com.akole.kmp.movies.data.service.database.MoviesDatabase
import platform.Foundation.NSHomeDirectory

// Specific Room Builder configuration for iOS
fun getDatabaseBuilder(): RoomDatabase.Builder<MoviesDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<MoviesDatabase>(
        name = dbFilePath,
        factory = { MoviesDatabase::class.instantiateImpl() },
    ).setDriver(BundledSQLiteDriver())
}