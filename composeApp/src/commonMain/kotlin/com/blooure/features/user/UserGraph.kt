package com.blooure.features.user

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blooure.features.user.add.AddUserScreen
import com.navigation.Destinations

fun NavGraphBuilder.userGraph() {

    composable<Destinations.UserList>() {
        UserListScreen()
    }

    composable<Destinations.AddUser>() {
        AddUserScreen()
    }
}