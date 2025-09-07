package com.blooure.features.user.add.contract

import com.blooure.UnidirectionalViewModel
import com.blooure.features.user.add.models.SnackbarUserOptions
import com.domain.models.User

interface AddUserContract : UnidirectionalViewModel<AddUserContract.State, AddUserContract.Event, AddUserContract.Effect> {

    data class State(
        val snackbarOptions: SnackbarUserOptions? = null,
        val showSnackbar: Boolean = false,
    )

    sealed class Event {
        data class OnAddUser(val user: User) : Event()
        data object OnStart: Event()

        data object OnHideSnackbar: Event()
        data object OnBack: Event()
    }

    sealed class Effect {
        data object Back : Effect()
    }
}