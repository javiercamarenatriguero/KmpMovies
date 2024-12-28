package com.akole.kmp.movies.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akole.kmp.movies.data.service.database.DATABASE_NAME
import com.akole.kmp.movies.data.service.database.MoviesDatabase

// Specific Room Builder configuration for Android
fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<MoviesDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<MoviesDatabase>(
        context = appContext,
        name = dbFile.absolutePath,
    )
}