package com.blooure.features.home.contract

import com.blooure.UnidirectionalViewModel
import com.blooure.features.home.models.HomeItems
import com.domain.models.BloodPressure
import com.domain.models.User
import com.navigation.Destinations

/**
 * Contract for the Home screen, defining the unidirectional data flow.
 *
 * This interface outlines the structure for the Home screen's state, events, and effects,
 * adhering to the unidirectional data flow pattern. It specifies what data the screen
 * displays (State), how the user can interact with the screen (Event), and what side
 * effects can be triggered by the screen (Effect).
 */
interface HomeContract : UnidirectionalViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect> {

    data class State(
        val items: List<HomeItems> = emptyList<HomeItems>(),
        val isBottomSheetVisible: Boolean = false
    )

    sealed class Event {
        data object OnStart : Event()
        data class ShowBottomSheet(val state: Boolean): Event()
        data class OnNavigate(val destination: Destinations): Event()
    }

    sealed class Effect {
        data class NavigateTo(val destination: Destinations) : Effect()
    }
}