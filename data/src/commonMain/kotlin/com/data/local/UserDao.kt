package com.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.data.models.UserDb

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(balanceDb: UserDb)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserDb>?

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUser(id: Int): UserDb?

    @Update
    suspend fun updateUser(user: UserDb)

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUser(id: Int)
}