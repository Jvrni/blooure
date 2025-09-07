package com.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalColors = staticCompositionLocalOf<BlooureColors> { error("No Colors provided") }
private val LocalTypography = staticCompositionLocalOf<androidx.compose.material3.Typography> { error("No Typography provided") }

val Colors: BlooureColors
    @Composable
    get() = LocalColors.current

val Typography: androidx.compose.material3.Typography
    @Composable
    get() = LocalTypography.current

@Composable
fun BlooureTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColors else lightColors
    val rememberedColors = remember { colors.copy() }.apply { updateColors(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides Typography()
    ) {
        content()
    }
}
