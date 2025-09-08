package com.domain.bloodPressure

import com.domain.models.BloodPressure
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving a flow of all blood pressure readings.
 *
 * This class encapsulates the logic for fetching blood pressure data from the repository.
 * It exposes an `invoke` operator to allow for a concise way to execute the use case.
 *
 * @property repository The [BloodPressureRepository] instance used to access blood pressure data.
 */
class GetBloodPressures(private val repository: BloodPressureRepository) {
    operator fun invoke(): Flow<List<BloodPressure>> {
        return repository.getBloodPressures()
    }
}