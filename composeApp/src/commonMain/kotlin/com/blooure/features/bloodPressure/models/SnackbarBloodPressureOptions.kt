package com.blooure.features.bloodPressure.models

import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.blood_pressure_snackbar_error
import blooure.composeapp.generated.resources.blood_pressure_snackbar_success
import blooure.composeapp.generated.resources.blood_pressure_snackbar_warning
import org.jetbrains.compose.resources.StringResource

enum class SnackbarBloodPressureOptions(val message: StringResource) {
    Success(Res.string.blood_pressure_snackbar_success),
    Error(Res.string.blood_pressure_snackbar_error),
    Warning(Res.string.blood_pressure_snackbar_warning),
}