package com.blooure.features.user.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.ic_plus
import blooure.composeapp.generated.resources.user_list_title
import com.blooure.features.user.list.contract.UserListContract
import com.designsystem.components.TopAppBar
import com.designsystem.theme.Colors
import com.navigation.Destinations
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserListScreen(state: UserListContract.State, event: (UserListContract.Event) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Colors.background,
        contentColor = Colors.onBackground,
        topBar = {
            TopAppBar(stringResource(Res.string.user_list_title)) {
                event.invoke(UserListContract.Event.OnBack)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                onClick = {
                    event.invoke(UserListContract.Event.OnNavigate(Destinations.AddUser))
                }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_plus),
                    contentDescription = ""
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {

    }
}