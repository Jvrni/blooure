package com.domain.user

import com.domain.models.User
import kotlinx.coroutines.flow.Flow

/**
 * Use case for adding a new user to the system.
 *
 * This class encapsulates the business logic for adding a user. It relies on a
 * `UserRepository` to persist the user data.
 *
 * @property repository The repository responsible for user data operations.
 */
class AddUser(private val repository: UserRepository) {

    operator fun invoke(user: User): Flow<Unit> {
        return repository.addUser(user)
    }
}