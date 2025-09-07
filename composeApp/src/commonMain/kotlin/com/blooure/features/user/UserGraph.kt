package com.blooure.features.user

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.blooure.collectInLaunchedEffect
import com.blooure.features.user.add.AddUserScreen
import com.blooure.features.user.add.AddUserViewModel
import com.blooure.features.user.add.contract.AddUserContract
import com.blooure.features.user.list.contract.UserListContract
import com.blooure.features.user.list.UserListScreen
import com.blooure.features.user.list.UserListViewModel
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
        val viewModel: AddUserViewModel = getKoin().get()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(AddUserContract.Event.OnStart)
        }

        effect.collectInLaunchedEffect { dispatch ->
            when (dispatch) {
                is AddUserContract.Effect.Back -> navController.popBackStack()
            }
        }

        AddUserScreen(state, event)
    }
}