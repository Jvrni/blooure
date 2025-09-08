package com.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.domain.models.BloodPressure

@Entity(tableName = "blood_pressure")
data class BloodPressureDb(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val systolic: Int,
    val diastolic: Int,
    val dateTime: String,
    val state: String,
    val userId: Long
) {
    fun mapToDomain(): BloodPressure {
        return BloodPressure(
            id = id,
            systolic = systolic,
            diastolic = diastolic,
            dateTime = dateTime,
            state = state,
            userId = userId
        )
    }
}

