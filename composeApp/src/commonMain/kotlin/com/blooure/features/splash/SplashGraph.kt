package com.blooure.features.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.navigation.Destinations

fun NavGraphBuilder.splashGraph(navController: NavHostController) {
    composable<Destinations.Splash> {
        SplashScreen()
    }
}
