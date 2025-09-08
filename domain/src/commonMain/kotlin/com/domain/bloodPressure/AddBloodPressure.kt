package com.domain.bloodPressure

import com.domain.models.BloodPressure
import kotlinx.coroutines.flow.Flow

/**
 * Use case for adding a new blood pressure reading.
 *
 * This class encapsulates the business logic for adding a blood pressure record
 * to the repository. It follows the Clean Architecture pattern by providing
 * a single responsibility for this specific action.
 *
 * @property repository The [BloodPressureRepository] responsible for data operations.
 */
class AddBloodPressure(private val repository: BloodPressureRepository) {
    operator fun invoke(bloodPressure: BloodPressure): Flow<Unit> {
        return repository.addBloodPressure(bloodPressure)
    }
}