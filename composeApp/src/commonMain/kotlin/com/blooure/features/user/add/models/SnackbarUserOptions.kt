package com.blooure.features.user.add.models

import blooure.composeapp.generated.resources.Res
import blooure.composeapp.generated.resources.add_user_snackbar_success
import blooure.composeapp.generated.resources.add_user_snackbar_error
import blooure.composeapp.generated.resources.add_user_snackbar_warning
import org.jetbrains.compose.resources.StringResource

enum class SnackbarUserOptions(val message: StringResource) {
    Success(Res.string.add_user_snackbar_success),
    Error(Res.string.add_user_snackbar_error),
    Warning(Res.string.add_user_snackbar_warning),
}