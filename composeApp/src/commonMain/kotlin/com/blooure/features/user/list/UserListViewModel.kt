package com.blooure.features.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blooure.features.user.list.contract.UserListContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel(), UserListContract {
    private val _state = MutableStateFlow(UserListContract.State())
    override val state: StateFlow<UserListContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<UserListContract.Effect>()
    override val effect: SharedFlow<UserListContract.Effect> = _effect.asSharedFlow()

    override fun event(event: UserListContract.Event) {
        when (event) {
            is UserListContract.Event.OnStart -> {
            }
            is UserListContract.Event.OnNavigate -> {
                viewModelScope.launch {
                    _effect.emit(UserListContract.Effect.NavigateTo(event.destination))
                }
            }
            is UserListContract.Event.OnBack -> {
                viewModelScope.launch {
                    _effect.emit(UserListContract.Effect.Back)
                }
            }
        }
    }
}