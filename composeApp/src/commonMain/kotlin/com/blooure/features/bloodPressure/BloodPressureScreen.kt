package com.blooure.features.bloodPressure

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.blood_pressure_button_label
import blooure.composeapp.generated.resources.blood_pressure_diastolic_pressure_label
import blooure.composeapp.generated.resources.blood_pressure_select_date_time_label
import blooure.composeapp.generated.resources.blood_pressure_select_user_label
import blooure.composeapp.generated.resources.blood_pressure_state_intense_activity
import blooure.composeapp.generated.resources.blood_pressure_state_light_activity
import blooure.composeapp.generated.resources.blood_pressure_state_rest
import blooure.composeapp.generated.resources.blood_pressure_systolic_pressure_label
import blooure.composeapp.generated.resources.blood_pressure_title
import com.blooure.features.bloodPressure.contents.bottomSheet.BloodPressureBottomSheet
import com.blooure.features.bloodPressure.contract.BloodPressureContract
import com.blooure.features.bloodPressure.models.SnackbarBloodPressureOptions
import com.designsystem.components.Snackbar
import com.designsystem.components.SnackbarOptions
import com.designsystem.components.TextField
import com.designsystem.components.TimePickerDialog
import com.designsystem.components.TopAppBar
import com.designsystem.theme.Colors
import com.domain.models.User
import kotlinx.datetime.DateTimeUnit.Companion.DAY
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalTime::class,
    FormatStringsInDatetimeFormats::class
)
@Composable
fun BloodPressureScreen(
    state: BloodPressureContract.State,
    event: (BloodPressureContract.Event) -> Unit
) {
    val defaultShape = RoundedCornerShape(24.dp)

    val systolic = remember { mutableStateOf("") }
    val diastolic = remember { mutableStateOf("") }
    val user = remember { mutableStateOf(User(name = "")) }
    val dateTime = remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()

    val options = listOf(
        stringResource(Res.string.blood_pressure_state_rest),
        stringResource(Res.string.blood_pressure_state_light_activity),
        stringResource(Res.string.blood_pressure_state_intense_activity),
    )

    val (selectedOption, setSelectedOption) = remember { mutableStateOf(options[0]) }

    Box(modifier = Modifier.fillMaxSize().background(Colors.background)) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopAppBar(stringResource(Res.string.blood_pressure_title)) {
                event.invoke(BloodPressureContract.Event.OnBack)
            }

            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                value = systolic.value,
                label = stringResource(Res.string.blood_pressure_systolic_pressure_label),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { value ->
                    systolic.value = value.filter { it.isDigit() }
                }
            )

            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                value = diastolic.value,
                label = stringResource(Res.string.blood_pressure_diastolic_pressure_label),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { value ->
                    diastolic.value = value.filter { it.isDigit() }
                }
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
                    .height(TextFieldDefaults.MinHeight)
                    .background(Colors.onBackground, shape = defaultShape)
                    .clip(defaultShape)
                    .clickable { event.invoke(BloodPressureContract.Event.ShowBottomSheet(true)) }
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                val label =
                    user.value.name.ifEmpty { stringResource(Res.string.blood_pressure_select_user_label) }
                Text(text = label)
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
                    .height(TextFieldDefaults.MinHeight)
                    .background(Colors.onBackground, shape = defaultShape)
                    .clip(defaultShape)
                    .clickable { event.invoke(BloodPressureContract.Event.ShowDatePicker(true)) }
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                val label =
                    dateTime.value.ifEmpty { stringResource(Res.string.blood_pressure_select_date_time_label) }
                Text(text = label)
            }

            options.forEach { option ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).clickable { setSelectedOption(option) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = { setSelectedOption(option) }
                    )
                    Text(text = option, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .align(Alignment.BottomCenter),
            onClick = {
                event.invoke(
                    BloodPressureContract.Event.OnAddBloodPressure(
                        systolic = systolic.value,
                        diastolic = diastolic.value,
                        dateTime = dateTime.value,
                        user = user.value,
                        state = selectedOption
                    )
                )
            }
        ) {
            Text(text = stringResource(Res.string.blood_pressure_button_label))
        }

        if (state.showBottomSheet)
            BloodPressureBottomSheet(
                state.users,
                onDismiss = { event.invoke(BloodPressureContract.Event.ShowBottomSheet(false)) }
            ) {
                event.invoke(BloodPressureContract.Event.ShowBottomSheet(false))
                user.value = it
            }

        if (state.showDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = { event.invoke(BloodPressureContract.Event.ShowDatePicker(false)) },
                confirmButton = {
                    Button(onClick = {
                        event.invoke(BloodPressureContract.Event.ShowDatePicker(false))
                        event.invoke(BloodPressureContract.Event.ShowTimePicker(true))
                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        event.invoke(BloodPressureContract.Event.ShowDatePicker(false))
                    }) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        if (state.showTimePickerDialog)
            TimePickerDialog(
                onDismiss = { event.invoke(BloodPressureContract.Event.ShowTimePicker(false)) },
            ) { hour, minute ->
                event.invoke(BloodPressureContract.Event.ShowTimePicker(false))

                datePickerState.selectedDateMillis?.let { dateMillis ->
                    val date = Instant.fromEpochMilliseconds(dateMillis).plus(1, DAY, TimeZone.UTC)
                        .toLocalDateTime(TimeZone.currentSystemDefault()).date

                    dateTime.value = LocalDateTime(
                        date,
                        LocalTime(hour, minute)
                    ).format(LocalDateTime.Format { byUnicodePattern("yyyy/MM/dd - HH:mm") })
                }
            }

        Snackbar(
            modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.TopCenter),
            snackbarOptions = state.snackbarOptions?.name?.let { SnackbarOptions.valueOf(it) },
            message = state.snackbarOptions?.message?.let { stringResource(it) } ?: ""
        ) {
            when (state.snackbarOptions) {
                SnackbarBloodPressureOptions.Success -> event.invoke(BloodPressureContract.Event.OnBack)
                SnackbarBloodPressureOptions.Warning, SnackbarBloodPressureOptions.Error -> event.invoke(
                    BloodPressureContract.Event.OnHideSnackbar
                )

                else -> {}
            }
        }
    }
}