package com.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.domain.models.User

@Entity(tableName = "users")
data class UserDb(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String
) {
    fun mapToDomain(): User {
        return User(id, name)
    }
}

