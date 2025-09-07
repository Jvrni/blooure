package com.blooure.features.user

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.blooure.collectInLaunchedEffect
import com.blooure.features.user.add.AddUserScreen
import com.blooure.features.user.contract.UserListContract
import com.blooure.use
import com.navigation.Destinations
import org.koin.compose.getKoin

fun NavGraphBuilder.userGraph(navController: NavHostController) {

    composable<Destinations.UserList>() {
        val viewModel: UserListViewModel = getKoin().get()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(UserListContract.Event.OnStart)
        }

        effect.collectInLaunchedEffect { dispatch ->
            when (dispatch) {
                is UserListContract.Effect.NavigateTo -> navController.navigate(dispatch.destination)
                is UserListContract.Effect.Back -> navController.popBackStack()
            }
        }

        UserListScreen(state, event)
    }

    composable<Destinations.AddUser>() {
        AddUserScreen()
    }
}