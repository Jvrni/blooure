package com.blooure.features.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blooure.features.user.list.contract.UserListContract
import com.domain.models.User
import com.domain.user.DeleteUser
import com.domain.user.GetUsers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUsers: GetUsers,
    private val deleteUser: DeleteUser
) : ViewModel(), UserListContract {
    private val _state = MutableStateFlow(UserListContract.State())
    override val state: StateFlow<UserListContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<UserListContract.Effect>()
    override val effect: SharedFlow<UserListContract.Effect> = _effect.asSharedFlow()

    override fun event(event: UserListContract.Event) {
        when (event) {
            is UserListContract.Event.OnStart -> {
                getUsers()
            }

            is UserListContract.Event.OnDelete -> {
                deleteUser(event.user)
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

    private fun getUsers() {
        viewModelScope.launch {
            getUsers.invoke()
                .catch {
                    _state.update { it.copy(users = emptyList(), showError = true) }
                    it.message
                }
                .collect { users ->
                    _state.update { it.copy(users = users, showError = false) }
                }
        }
    }

    private fun deleteUser(user: User) {
        viewModelScope.launch {
            deleteUser.invoke(user)
                .catch {

                }
                .collect {
                    getUsers()
                }
        }
    }
}