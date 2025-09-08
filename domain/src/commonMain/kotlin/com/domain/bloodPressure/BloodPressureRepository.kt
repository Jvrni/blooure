package com.domain.bloodPressure

import com.domain.models.BloodPressure
import kotlinx.coroutines.flow.Flow

interface BloodPressureRepository {
    fun addBloodPressure(bloodPressure: BloodPressure): Flow<Unit>

    fun getBloodPressures(): Flow<List<BloodPressure>>

    fun deleteBloodPressures(userId: Long): Flow<Unit>
}