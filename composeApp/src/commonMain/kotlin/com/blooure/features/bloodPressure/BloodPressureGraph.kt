package com.blooure.features.bloodPressure

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.blooure.collectInLaunchedEffect
import com.blooure.features.bloodPressure.contract.BloodPressureContract
import com.blooure.use
import com.navigation.Destinations
import org.koin.compose.getKoin

fun NavGraphBuilder.bloodPressureGraph(navController: NavHostController) {
    composable<Destinations.BloodPressure>() {
        val viewModel: BloodPressureViewModel = getKoin().get()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(BloodPressureContract.Event.OnStart)
        }

        effect.collectInLaunchedEffect { dispatch ->
            when (dispatch) {
                is BloodPressureContract.Effect.Back -> navController.popBackStack()
            }
        }

        BloodPressureScreen(state, event)
    }
}