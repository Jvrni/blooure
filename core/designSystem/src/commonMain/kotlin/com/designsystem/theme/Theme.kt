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

/**
 * BlooureTheme is the main entry point for applying the Blooure design system to a Composable UI.
 * It provides the appropriate color scheme (dark or light) and typography to its content.
 *
 * This function uses [CompositionLocalProvider] to make [BlooureColors] and Material 3 [Typography]
 * available to all Composables within its `content` lambda.
 *
 * The color scheme is determined by the `darkTheme` parameter, which defaults to the system's
 * dark theme setting. The chosen colors are then remembered across recompositions to avoid
 * unnecessary recalculations.
 *
 * @param darkTheme A boolean indicating whether to use the dark color scheme.
 *                  Defaults to the system's dark theme setting via [isSystemInDarkTheme].
 * @param content The Composable content that will inherit the Blooure theme.
 */
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
