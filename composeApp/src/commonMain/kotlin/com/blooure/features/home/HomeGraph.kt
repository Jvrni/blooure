package com.blooure.features.home

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.blooure.collectInLaunchedEffect
import com.blooure.features.home.contract.HomeContract
import com.blooure.use
import com.navigation.Destinations
import org.koin.compose.getKoin

/**
 * Defines the navigation graph for the home feature.
 *
 * This function sets up the composable for the home screen, including:
 * - Retrieving the [HomeViewModel] using Koin.
 * - Using the `use` function to manage the state, events, and effects of the ViewModel.
 * - Launching an effect to trigger the `OnStart` event when the composable is first displayed.
 * - Collecting effects from the ViewModel and handling navigation actions.
 * - Displaying the [HomeScreen] with the current state and event handler.
 *
 * @param navController The [NavHostController] used for navigation.
 */
fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable<Destinations.Home> {
        val viewModel: HomeViewModel = getKoin().get()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(HomeContract.Event.OnStart)
        }

        effect.collectInLaunchedEffect { dispatch ->
            when (dispatch) {
                is HomeContract.Effect.NavigateTo -> navController.navigate(dispatch.destination)
            }
        }

        HomeScreen(state, event)
    }
}