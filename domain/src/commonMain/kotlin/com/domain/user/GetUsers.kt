package com.domain.user

import com.domain.models.User
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving a list of users.
 *
 * This class encapsulates the logic for fetching users from the repository.
 * It provides a single `invoke` operator function that returns a [Flow]
 * emitting a list of [User] objects.
 *
 * @property repository The [UserRepository] instance used to fetch user data.
 */
class GetUsers(private val repository: UserRepository) {

    operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }
}