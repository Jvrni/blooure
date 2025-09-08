package com.domain.bloodPressure

import com.domain.models.BloodPressure
import kotlinx.coroutines.flow.Flow

class GetBloodPressures(private val repository: BloodPressureRepository) {
    operator fun invoke(): Flow<List<BloodPressure>> {
        return repository.getBloodPressures()
    }
}