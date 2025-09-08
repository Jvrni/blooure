package com.domain.bloodPressure

import com.domain.models.BloodPressure
import kotlinx.coroutines.flow.Flow

class AddBloodPressure(private val repository: BloodPressureRepository) {
    operator fun invoke(bloodPressure: BloodPressure): Flow<Unit> {
        return repository.addBloodPressure(bloodPressure)
    }
}