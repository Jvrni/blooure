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

/**
 * Defines the navigation graph for the Blood Pressure feature.
 *
 * This function sets up the composable for the Blood Pressure screen within the NavGraph.
 * It initializes the [BloodPressureViewModel], observes its state, events, and effects,
 * and renders the [BloodPressureScreen].
 *
 * When the screen is started, it triggers the [BloodPressureContract.Event.OnStart] event.
 * It also handles the [BloodPressureContract.Effect.Back] effect by popping the back stack
 * of the provided [navController].
 *
 * @param navController The [NavHostController] used for navigation within the graph.
 */
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