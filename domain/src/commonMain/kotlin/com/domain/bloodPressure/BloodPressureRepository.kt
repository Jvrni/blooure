package com.domain.bloodPressure

import com.domain.models.BloodPressure
import kotlinx.coroutines.flow.Flow

interface BloodPressureRepository {
    fun addBloodPressure(bloodPressure: BloodPressure): Flow<Unit>
}