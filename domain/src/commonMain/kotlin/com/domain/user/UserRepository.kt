package com.domain.user

import com.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun addUser(user: User): Flow<Unit>
    fun getUsers(): Flow<List<User>>
    fun deleteUser(user: User): Flow<Unit>

}