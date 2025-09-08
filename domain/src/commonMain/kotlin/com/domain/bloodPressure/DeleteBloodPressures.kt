package com.domain.bloodPressure

import kotlinx.coroutines.flow.Flow

/**
 * Use case for deleting all blood pressures associated with a specific user.
 *
 * This class encapsulates the business logic for deleting blood pressure records.
 * It interacts with the `BloodPressureRepository` to perform the deletion operation.
 *
 * @property repository The repository responsible for blood pressure data operations.
 */
class DeleteBloodPressures(private val repository: BloodPressureRepository) {
    operator fun invoke(userId: Long): Flow<Unit> {
        return repository.deleteBloodPressures(userId)
    }
}