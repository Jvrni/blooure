package com.blooure

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Represents a Unidirectional ViewModel that follows the MVI (Model-View-Intent) pattern.
 *
 * @param STATE The type of the state emitted by the ViewModel.
 * @param EVENT The type of events that can be sent to the ViewModel.
 * @param EFFECT The type of side effects that the ViewModel can produce.
 */
interface UnidirectionalViewModel<STATE, EVENT, EFFECT> {
    val state: StateFlow<STATE>
    val effect: SharedFlow<EFFECT>
    fun event(event: EVENT)
}

@Composable
inline fun <reified STATE, EVENT, EFFECT> use(
    viewModel: UnidirectionalViewModel<STATE, EVENT, EFFECT>
): StateDispatchEffect<STATE, EVENT, EFFECT> {
    val state by viewModel.state.collectAsState()

    val dispatch: (EVENT) -> Unit = { event ->
        viewModel.event(event)
    }

    return StateDispatchEffect(
        state = state,
        dispatch = dispatch,
        effectFlow = viewModel.effect
    )
}

@Composable
fun <T> SharedFlow<T>.collectInLaunchedEffect(function: suspend (value: T) -> Unit) {
    val flow = this
    LaunchedEffect(key1 = flow) {
        flow.collect(function)
    }
}

data class StateDispatchEffect<STATE, EVENT, EFFECT>(
    val state: STATE,
    val dispatch: (EVENT) -> Unit,
    val effectFlow: SharedFlow<EFFECT>
)