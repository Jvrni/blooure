package com.data.repository

import com.data.local.UserDao
import com.data.models.UserDb
import com.domain.models.User
import com.domain.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(val database: UserDao) : UserRepository {
    override fun addUser(user: User): Flow<Unit> {
        return flow {
            emit(
                database.createUser(
                    UserDb(
                        id = user.id,
                        name = user.name
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getUsers(): Flow<List<User>> {
        return flow {
            emit(
                database.getUsers()?.map { userDb -> userDb.mapToDomain() }
                    ?: emptyList()
            )
        }.flowOn(Dispatchers.IO)
    }
}