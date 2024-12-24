package com.akole.kmp.movies.ui.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
){
    MaterialTheme {
        Surface(
            modifier = modifier.fillMaxHeight(),
            content = content,
        )
    }
}
