package com.domain.bloodPressure

import kotlinx.coroutines.flow.Flow

class DeleteBloodPressures(private val repository: BloodPressureRepository) {
    operator fun invoke(userId: Long): Flow<Unit> {
        return repository.deleteBloodPressures(userId)
    }
}