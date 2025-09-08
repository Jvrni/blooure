package com.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.domain.models.BloodPressure
import com.domain.models.User

@Entity(tableName = "blood_pressure")
data class BloodPressureDb(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val systolic: Int,
    val diastolic: Int,
    val date: String,
    val time: String,
    val userId: Long
) {
    fun mapToDomain(): BloodPressure {
        return BloodPressure(
            id = id,
            systolic = systolic,
            diastolic = diastolic,
            date = date,
            time = time,
            userId = userId
        )
    }
}

