package com.blooure.features.bloodPressure

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.navigation.Destinations

fun NavGraphBuilder.bloodPressureGraph() {
    composable<Destinations.BloodPressure>() {
        BloodPressureScreen()

    }
}