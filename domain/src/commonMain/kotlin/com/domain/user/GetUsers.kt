package com.domain.user

import com.domain.models.User
import kotlinx.coroutines.flow.Flow

class GetUsers(private val repository: UserRepository) {

    operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }
}