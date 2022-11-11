package com.vsebastianvc.tsnote.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = TSYellow,
    surface = TSYellow,
    onPrimary = Color.Black,
    onSurface = Color.Black


)

@Composable
fun TSNoteTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}