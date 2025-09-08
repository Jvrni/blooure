package com.blooure.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.home_blood_pressure_label
import blooure.composeapp.generated.resources.home_description_pressure
import blooure.composeapp.generated.resources.home_list_empty_label
import blooure.composeapp.generated.resources.ic_plus
import blooure.composeapp.generated.resources.img_empty_blood_pressure
import com.blooure.features.home.contents.HomeBottomSheet
import com.blooure.features.home.contract.HomeContract
import com.designsystem.theme.Colors
import com.designsystem.theme.Typography
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun HomeScreen(state: HomeContract.State, event: (HomeContract.Event) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Colors.background,
        contentColor = Colors.onBackground,
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                onClick = { event.invoke(HomeContract.Event.ShowBottomSheet(true)) }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_plus),
                    contentDescription = ""
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                if (state.items.isEmpty())
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            modifier = Modifier.size(350.dp).padding(top = 100.dp),
                            painter = painterResource(Res.drawable.img_empty_blood_pressure),
                            contentDescription = "",
                        )

                        Text(
                            text = stringResource(Res.string.home_list_empty_label),
                            style = Typography.bodyMedium.copy(fontSize = 16.sp),
                            color = Colors.outline
                        )
                    }
            }

            items(state.items) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),

                        ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = item.user.name,
                                style = Typography.bodyMedium
                            )
                            Text(
                                text = item.bloodPressure.dateTime,
                                style = Typography.bodyMedium
                            )
                        }

                        Text(
                            text = stringResource(
                                Res.string.home_blood_pressure_label,
                                item.bloodPressure.systolic,
                                item.bloodPressure.diastolic
                            )
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                            text = stringResource(
                                Res.string.home_description_pressure,
                                item.user.name,
                                item.bloodPressure.dateTime,
                                item.bloodPressure.state
                            )
                        )
                    }
                }
            }
        }

        if (state.isBottomSheetVisible)
            HomeBottomSheet(
                onDismiss = { event.invoke(HomeContract.Event.ShowBottomSheet(false)) }
            ) {
                event.invoke(HomeContract.Event.ShowBottomSheet(false))
                event.invoke(HomeContract.Event.OnNavigate(it))
            }
    }
}