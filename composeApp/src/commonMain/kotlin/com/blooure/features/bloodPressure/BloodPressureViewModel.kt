package com.blooure.features.bloodPressure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blooure.features.bloodPressure.contract.BloodPressureContract
import com.blooure.features.bloodPressure.models.SnackbarBloodPressureOptions
import com.domain.bloodPressure.AddBloodPressure
import com.domain.models.BloodPressure
import com.domain.models.User
import com.domain.user.GetUsers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

/**
 * ViewModel for the Blood Pressure feature.
 *
 * This ViewModel handles the business logic for recording and managing blood pressure readings.
 * It interacts with use cases to add blood pressure data and retrieve user information.
 * It exposes state and effects to the UI through [StateFlow] and [SharedFlow] respectively,
 * following the MVI (Model-View-Intent) pattern via the [BloodPressureContract].
 *
 * @property addBloodPressure Use case for adding a new blood pressure reading.
 * @property getUsers Use case for fetching the list of users.
 */
class BloodPressureViewModel(
    private val addBloodPressure: AddBloodPressure,
    private val getUsers: GetUsers
) : ViewModel(), BloodPressureContract {

    private val _state = MutableStateFlow(BloodPressureContract.State())
    override val state: StateFlow<BloodPressureContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<BloodPressureContract.Effect>()
    override val effect: SharedFlow<BloodPressureContract.Effect> = _effect.asSharedFlow()

    override fun event(event: BloodPressureContract.Event) {
        when (event) {
            is BloodPressureContract.Event.OnStart -> {
                viewModelScope.launch {
                    _state.update { BloodPressureContract.State() }
                }
            }

            is BloodPressureContract.Event.OnAddBloodPressure -> {
                addBloodPressure(
                    user = event.user,
                    systolic = event.systolic,
                    diastolic = event.diastolic,
                    dateTime = event.dateTime,
                    state = event.state
                )
            }

            is BloodPressureContract.Event.ShowBottomSheet -> {
                _state.update { it.copy(showBottomSheet = event.isShowBottomSheet) }

                if (event.isShowBottomSheet)
                    getUsers()
            }

            is BloodPressureContract.Event.ShowDatePicker -> {
                _state.update { it.copy(showDatePickerDialog = event.isShowDatePicker) }
            }

            is BloodPressureContract.Event.ShowTimePicker -> {
                _state.update { it.copy(showTimePickerDialog = event.isShowTimePicker) }
            }

            is BloodPressureContract.Event.OnHideSnackbar -> {
                viewModelScope.launch {
                    _state.update { it.copy(snackbarOptions = null, showSnackbar = false) }
                }
            }

            is BloodPressureContract.Event.OnBack -> {
                viewModelScope.launch {
                    _effect.emit(BloodPressureContract.Effect.Back)
                }
            }
        }
    }

    @OptIn(FormatStringsInDatetimeFormats::class)
    private fun addBloodPressure(
        user: User,
        systolic: String,
        diastolic: String,
        dateTime: String,
        state: String,
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    snackbarOptions = null,
                    showSnackbar = false
                )
            }

            if (dateTime.isEmpty() || user.name.isBlank() || systolic.isBlank() || diastolic.isBlank()) {
                _state.update {
                    it.copy(
                        snackbarOptions = SnackbarBloodPressureOptions.Warning,
                        showSnackbar = true
                    )
                }

                return@launch
            }

            addBloodPressure.invoke(
                BloodPressure(
                    userId = user.id,
                    systolic = systolic.toInt(),
                    diastolic = diastolic.toInt(),
                    dateTime = dateTime,
                    state = state
                )
            )
                .catch {
                    _state.update {
                        it.copy(
                            snackbarOptions = SnackbarBloodPressureOptions.Error,
                            showSnackbar = true
                        )
                    }
                }
                .collect {
                    _state.update {
                        it.copy(
                            snackbarOptions = SnackbarBloodPressureOptions.Success,
                            showSnackbar = true
                        )
                    }
                }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsers.invoke()
                .catch {
                }
                .collect { users ->
                    _state.update { it.copy(users = users) }
                }
        }
    }
}