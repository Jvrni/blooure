package com.blooure.features.bloodPressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.blood_pressure_button_label
import blooure.composeapp.generated.resources.blood_pressure_diastolic_pressure_label
import blooure.composeapp.generated.resources.blood_pressure_select_date_time_label
import blooure.composeapp.generated.resources.blood_pressure_select_user_label
import blooure.composeapp.generated.resources.blood_pressure_systolic_pressure_label
import blooure.composeapp.generated.resources.blood_pressure_title
import com.designsystem.components.TopAppBar
import com.designsystem.theme.Colors
import org.jetbrains.compose.resources.stringResource

@Composable
fun BloodPressureScreen() {
    val defaultShape = RoundedCornerShape(24.dp)

    Box(modifier = Modifier.fillMaxSize().background(Colors.background)) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopAppBar(stringResource(Res.string.blood_pressure_title)) {

            }

            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                shape = defaultShape,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = "",
                label = {
                    Text(text = stringResource(Res.string.blood_pressure_systolic_pressure_label))
                },
                onValueChange = {

                }
            )

            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                shape = defaultShape,
                value = "",
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                label = {
                    Text(text = stringResource(Res.string.blood_pressure_diastolic_pressure_label))
                },
                onValueChange = {

                }
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
                    .height(TextFieldDefaults.MinHeight)
                    .background(Colors.onBackground, shape = defaultShape)
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = stringResource(Res.string.blood_pressure_select_user_label))
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
                    .height(TextFieldDefaults.MinHeight)
                    .background(Colors.onBackground, shape = defaultShape)
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = stringResource(Res.string.blood_pressure_select_date_time_label))
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .align(Alignment.BottomCenter),
            onClick = {}
        ) {
            Text(text = stringResource(Res.string.blood_pressure_button_label))
        }
    }
}