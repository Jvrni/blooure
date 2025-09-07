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
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.blooure.features.splash.splashGraph
import com.designsystem.theme.BlooureTheme
import com.designsystem.theme.Colors
import com.navigation.Destinations

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
                startDestination = Destinations.Splash
            ) {
                splashGraph(navController)

            }
        }
    }
}