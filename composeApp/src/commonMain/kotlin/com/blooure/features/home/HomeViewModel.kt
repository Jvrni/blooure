package com.blooure.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blooure.features.home.contract.HomeContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(), HomeContract {
    private val _state = MutableStateFlow(HomeContract.State())
    override val state: StateFlow<HomeContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    override val effect: SharedFlow<HomeContract.Effect> = _effect.asSharedFlow()

    override fun event(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnStart -> {
            }

            is HomeContract.Event.ShowBottomSheet -> {
                _state.value = _state.value.copy(isBottomSheetVisible = event.state)
            }

            is HomeContract.Event.OnNavigate -> {
                viewModelScope.launch {
                    _effect.emit(HomeContract.Effect.NavigateTo(event.destination))
                }
            }
        }
    }
}