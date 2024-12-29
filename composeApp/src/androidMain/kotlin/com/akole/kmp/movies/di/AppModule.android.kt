package com.akole.kmp.movies.di

import com.akole.kmp.movies.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder(get()) }
}
