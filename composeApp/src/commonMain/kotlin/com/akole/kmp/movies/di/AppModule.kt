package com.akole.kmp.movies.di

import androidx.room.RoomDatabase
import com.akole.kmp.movies.BuildConfig
import com.akole.kmp.movies.data.repository.MoviesRepositoryImpl
import com.akole.kmp.movies.data.service.database.MoviesDao
import com.akole.kmp.movies.data.service.database.MoviesDatabase
import com.akole.kmp.movies.data.service.network.MoviesService
import com.akole.kmp.movies.domain.repository.MoviesRepository
import com.akole.kmp.movies.ui.screens.home.HomeViewModel
import com.akole.kmp.movies.ui.screens.detail.DetailViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single(named("apiKey")) { BuildConfig.API_KEY }
    single<MoviesDao> {
        val dbBuilder = get<RoomDatabase.Builder<MoviesDatabase>>()
        dbBuilder.build().moviesDao()
    }
}

val dataModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    parameters.append("api_key", get(named("apiKey")))
                }
            }
        }
    }

    single { MoviesService(client = get()) }

    single<MoviesRepository> {
        MoviesRepositoryImpl(
            moviesDao = get(),
            moviesService = get(),
        )
    }
}

val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}

// This will be implemented in the platform-specific modules
expect val nativeModule : Module

// Config allows to add specific config for each platform if needed
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, dataModule, viewModelsModule, nativeModule)
    }
}