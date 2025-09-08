package com.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.designsystem.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onTimeSelected: (Int, Int) -> Unit
) {
    val timeState = rememberTimePickerState(is24Hour = true)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Dialog(
            onDismissRequest = { onDismiss.invoke() },
            properties = DialogProperties(usePlatformDefaultWidth = true)
        ) {
            ElevatedCard(
                modifier = Modifier
                    .background(
                        color = Colors.surface,
                        shape = RoundedCornerShape(24.dp)
                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = "Select time"
                    )
                    TimePicker(
                        state = timeState,
                        layoutType = TimePickerLayoutType.Vertical,
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            modifier = Modifier.padding(end = 8.dp),
                            onClick = { onDismiss.invoke() }
                        ) {
                            Text(
                                text = "Cancel"
                            )
                        }
                        Button(
                            modifier = Modifier.padding(start = 8.dp),
                            onClick = {
                                onTimeSelected.invoke(timeState.hour, timeState.minute)
                            }
                        ) {
                            Text(text = "OK")
                        }
                    }
                }
            }
        }
    }
}