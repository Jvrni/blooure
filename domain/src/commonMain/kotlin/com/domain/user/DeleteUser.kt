package com.domain.user

import com.domain.models.User
import kotlinx.coroutines.flow.Flow

class DeleteUser(private val repository: UserRepository) {

    operator fun invoke(user: User): Flow<Unit> {
        return repository.deleteUser(user)
    }
}