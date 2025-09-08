package com.blooure

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.blooure.di.appModule
import com.blooure.features.bloodPressure.bloodPressureGraph
import com.blooure.features.home.homeGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.blooure.features.user.userGraph
import com.designsystem.theme.BlooureTheme
import com.designsystem.theme.Colors
import com.navigation.Destinations
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoinIos() = initKoin(appDeclaration = {})

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(appModule())
    }
}

/**
 * The main entry point of the application.
 *
 * This composable function sets up the overall theme, surface, and navigation
 * for the Blooure app. It initializes a [NavController] and uses a [NavHost]
 * to define the different navigation graphs (home, blood pressure, user).
 *
 * The `BlooureTheme` provides the visual styling for the app.
 * The `Surface` acts as the main container with a background color.
 * `WindowInsets.safeDrawing` is applied to ensure content is displayed within safe areas.
 *
 * The navigation starts at the `Destinations.Home` screen.
 */
@Composable
@Preview
fun App() {
    BlooureTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Colors.background
        ) {
            val navController = rememberNavController()

            NavHost(
                modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
                navController = navController,
                startDestination = Destinations.Home
            ) {
                homeGraph(navController)
                bloodPressureGraph(navController)
                userGraph(navController)
            }
        }
    }
}