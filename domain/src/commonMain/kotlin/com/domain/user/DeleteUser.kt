package com.domain.user

import com.domain.models.User
import kotlinx.coroutines.flow.Flow

/**
 * Use case for deleting a user.
 *
 * This class encapsulates the business logic for deleting a user from the system.
 * It interacts with the [UserRepository] to perform the actual deletion.
 *
 * @property repository The [UserRepository] instance used to access user data.
 */
class DeleteUser(private val repository: UserRepository) {

    operator fun invoke(user: User): Flow<Unit> {
        return repository.deleteUser(user)
    }
}