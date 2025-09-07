package com.blooure.features.user.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blooure.features.user.add.contract.AddUserContract
import com.blooure.features.user.add.models.SnackbarUserOptions
import com.domain.models.User
import com.domain.user.AddUser
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddUserViewModel(private val addUser: AddUser) : ViewModel(), AddUserContract {

    private val _state = MutableStateFlow(AddUserContract.State())
    override val state: StateFlow<AddUserContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AddUserContract.Effect>()
    override val effect: SharedFlow<AddUserContract.Effect> = _effect.asSharedFlow()

    override fun event(event: AddUserContract.Event) {
        when (event) {
            is AddUserContract.Event.OnStart -> {
                viewModelScope.launch {
                    _state.update { AddUserContract.State() }
                }
            }

            is AddUserContract.Event.OnHideSnackbar -> {
                viewModelScope.launch {
                    _state.update { it.copy(snackbarOptions = null, showSnackbar = false) }
                }
            }
            is AddUserContract.Event.OnAddUser -> {
                addUser(event.user)
            }

            is AddUserContract.Event.OnBack -> {
                viewModelScope.launch {
                    _effect.emit(AddUserContract.Effect.Back)
                }
            }
        }
    }

    private fun addUser(user: User) {
        viewModelScope.launch {
            _state.update { it.copy(snackbarOptions = null, showSnackbar = false) }

            if (user.name.isBlank()) {
                _state.update { it.copy(snackbarOptions = SnackbarUserOptions.Warning, showSnackbar = true) }
                return@launch
            }

            addUser.invoke(user)
                .catch { error ->
                    _state.update {
                         it.copy(snackbarOptions = SnackbarUserOptions.Error, showSnackbar = true)
                    }
                    error.message
                }
                .collect {
                    _state.update {
                        it.copy(snackbarOptions = SnackbarUserOptions.Success, showSnackbar = true)
                    }
                }
        }
    }
}