package com.akole.kmp.movies.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
){
    val isDarkTheme = isSystemInDarkTheme()
    val colorTheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    MaterialTheme(colorTheme) {
        Surface(
            modifier = modifier.fillMaxHeight(),
            content = content,
        )
    }
}
