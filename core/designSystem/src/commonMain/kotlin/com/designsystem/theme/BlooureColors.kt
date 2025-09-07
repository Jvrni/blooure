package com.designsystem.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

private val Green50 = Color(0xFF02BC87)
private val Red50 = Color(0xFFEA5B5B)
private val Blue50 = Color(0xFF009DCD)
private val DarkBlue50 = Color(0xFF070A56)
private val YellowGreenSecondary = Color(0xFF6EF633)
private val Grey10 = Color(0xffffffff)
private val Grey20 = Color(0xfffcfcfc)
private val GreyECECEC = Color(0xFFECECEC)
private val Black = Color(0xff000000)
private val White = Color(0xffffffff)


val lightColors = BlooureColors(
    primary = Green50,
    onPrimary = White,
    error = Red50,
    onError = White,
    text = DarkBlue50,
    onText = White,
    warning = YellowGreenSecondary,
    onWarning = White,
    background = Grey10,
    onBackground = GreyECECEC,
    surface = White,
    onSurface = Black,
    outline = Grey20,
    isLight = true,
)

val darkColors = BlooureColors(
    primary = Green50,
    onPrimary = Black,
    error = Red50,
    onError = White,
    text = DarkBlue50,
    onText = White,
    warning = YellowGreenSecondary,
    onWarning = White,
    background = Grey10,
    onBackground = Black,
    surface = Black,
    onSurface = White,
    outline = Grey20,
    isLight = false,
)

class BlooureColors(
    primary: Color,
    onPrimary: Color,
    error: Color,
    onError: Color,
    text: Color,
    onText: Color,
    warning: Color,
    onWarning: Color,
    background: Color,
    onBackground: Color,
    surface: Color,
    onSurface: Color,
    outline: Color,
    isLight: Boolean,
) {
    var primary by mutableStateOf(primary)
        private set

    var onPrimary by mutableStateOf(onPrimary)
        private set

    var error by mutableStateOf(error)
        private set

    var onError by mutableStateOf(onError)
        private set

    var success by mutableStateOf(text)
        private set

    var onSuccess by mutableStateOf(onText)
        private set

    var warning by mutableStateOf(warning)
        private set

    var onWarning by mutableStateOf(onWarning)
        private set

    var background by mutableStateOf(background)
        private set

    var onBackground by mutableStateOf(onBackground)
        private set

    var surface by mutableStateOf(surface)
        private set

    var onSurface by mutableStateOf(onSurface)
        private set

    var outline by mutableStateOf(outline)
        private set

    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        primary: Color = this.primary,
        onPrimary: Color = this.onPrimary,
        error: Color = this.error,
        onError: Color = this.onError,
        success: Color = this.success,
        onSuccess: Color = this.onSuccess,
        warning: Color = this.warning,
        onWarning: Color = this.onWarning,
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        surface: Color = this.surface,
        onSurface: Color = this.onSurface,
        outline: Color = this.outline,
        isLight: Boolean = this.isLight,
    ): BlooureColors = BlooureColors(
        primary,
        onPrimary,
        error,
        onError,
        success,
        onSuccess,
        warning,
        onWarning,
        background,
        onBackground,
        surface,
        onSurface,
        outline,
        isLight,
    )

    fun updateColors(other: BlooureColors) {
        primary = other.primary
        onPrimary = other.onPrimary
        error = other.error
        onError = other.onError
        success = other.success
        onSuccess = other.onSuccess
        warning = other.warning
        onWarning = other.onWarning
        background = other.background
        onBackground = other.onBackground
        surface = other.surface
        onSurface = other.onSurface
        outline = other.outline
        isLight = other.isLight
    }
}

