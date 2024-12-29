package com.akole.kmp.movies

import android.app.Application
import com.akole.kmp.movies.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            // Show Koin info on Logger
            androidLogger(Level.DEBUG)
            // Add context to Koin for those context dependent classes
            androidContext(this@MoviesApplication)
        }
    }
}