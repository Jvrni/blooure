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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.ic_plus
import com.blooure.features.home.contents.bottomSheet.HomeBottomSheet
import com.designsystem.theme.Colors
import com.navigation.Destinations
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (Destinations) -> Unit) {
    val isVisibleBottomSheet = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Colors.background,
        contentColor = Colors.onBackground,
        floatingActionButton = {
            AnimatedVisibility(
                visible = !isVisibleBottomSheet.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                    onClick = {
                        isVisibleBottomSheet.value = !isVisibleBottomSheet.value
                    }
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
            isVisible = isVisibleBottomSheet.value,
            onDismiss = { isVisibleBottomSheet.value = false }
        ) {
            onNavigate.invoke(it)
        }
    }
}