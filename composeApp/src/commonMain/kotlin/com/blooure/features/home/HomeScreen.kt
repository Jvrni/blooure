package com.blooure.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.blooure.features.home.contents.bottomSheet.HomeBottomSheet
import com.blooure.features.home.contract.HomeContract
import com.designsystem.theme.Colors
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(state: HomeContract.State, event: (HomeContract.Event) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Colors.background,
        contentColor = Colors.onBackground,
        floatingActionButton = {
            AnimatedVisibility(
                visible = !state.isBottomSheetVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                    onClick = { event.invoke(HomeContract.Event.ShowBottomSheet(true)) }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_plus),
                        contentDescription = ""
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        HomeBottomSheet(
            isVisible = state.isBottomSheetVisible,
            onDismiss = { event.invoke(HomeContract.Event.ShowBottomSheet(false)) }
        ) {
            event.invoke(HomeContract.Event.OnNavigate(it))
        }
    }
}