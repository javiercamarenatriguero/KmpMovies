package com.akole.kmp.movies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform