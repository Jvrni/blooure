package com.blooure.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.navigation.Destinations

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable<Destinations.Home> {
        HomeScreen() {
            navController.navigate(it)
        }
    }
}