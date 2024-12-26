package com.akole.kmp.movies.theme.dimens

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val viewSpacing: Dp,
    val screenSpacing: Dp,
)

val smallDimensions = Dimensions(
    viewSpacing = 8.dp,
    screenSpacing = 16.dp,
)

val mediumDimensions = Dimensions(
    viewSpacing = 8.dp,
    screenSpacing = 16.dp,
)

val largeDimensions = Dimensions(
    viewSpacing = 16.dp,
    screenSpacing = 32.dp,
)

val LocalAppDimens = staticCompositionLocalOf {
    smallDimensions
}