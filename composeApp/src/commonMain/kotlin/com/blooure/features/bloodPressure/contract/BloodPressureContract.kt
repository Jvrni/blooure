package com.blooure.features.bloodPressure.contract

import com.blooure.UnidirectionalViewModel
import com.blooure.features.bloodPressure.models.SnackbarBloodPressureOptions
import com.domain.models.User

interface BloodPressureContract : UnidirectionalViewModel<BloodPressureContract.State, BloodPressureContract.Event, BloodPressureContract.Effect> {

    data class State(
        val users: List<User> = emptyList(),
        val snackbarOptions: SnackbarBloodPressureOptions? = null,
        val showSnackbar: Boolean = false,
        val showBottomSheet: Boolean = false,
        val showDatePickerDialog: Boolean = false,
        val showTimePickerDialog: Boolean = false,
    )

    sealed class Event {
        data object OnStart : Event()
        data class OnAddBloodPressure(
            val systolic: String,
            val diastolic: String,
            val date: String,
            val time: String,
            val userId: Long
        ) : Event()
        data class ShowBottomSheet(val isShowBottomSheet: Boolean): Event()
        data class ShowDatePicker(val isShowDatePicker: Boolean): Event()
        data class ShowTimePicker(val isShowTimePicker: Boolean): Event()
        data object OnHideSnackbar: Event()
        data object OnBack: Event()
    }

    sealed class Effect {
        data object Back : Effect()
    }
}