package com.blooure.features.user.list.contract

import com.blooure.UnidirectionalViewModel
import com.domain.models.User
import com.navigation.Destinations

interface UserListContract : UnidirectionalViewModel<UserListContract.State, UserListContract.Event, UserListContract.Effect> {

    data class State(
        val users: List<User> = emptyList(),
        val showError: Boolean = false,
    )

    sealed class Event {
        data object OnStart : Event()
        data class OnDelete(val user: User) : Event()
        data class OnNavigate(val destination: Destinations): Event()
        data object OnBack: Event()
    }

    sealed class Effect {
        data class NavigateTo(val destination: Destinations) : Effect()
        data object Back : Effect()
    }
}