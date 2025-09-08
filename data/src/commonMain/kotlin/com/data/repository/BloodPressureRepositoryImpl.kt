package com.data.repository

import com.data.local.BloodPressureDao
import com.data.models.BloodPressureDb
import com.domain.bloodPressure.BloodPressureRepository
import com.domain.models.BloodPressure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Implementation of [BloodPressureRepository] that uses a local database to store and retrieve blood pressure data.
 *
 * @property database The Data Access Object (DAO) for interacting with the blood pressure table in the database.
 */
class BloodPressureRepositoryImpl(val database: BloodPressureDao) : BloodPressureRepository {
    override fun addBloodPressure(bloodPressure: BloodPressure): Flow<Unit> {
        return flow {
            emit(
                database.createBloodPressure(
                    BloodPressureDb(
                        id = bloodPressure.id,
                        systolic = bloodPressure.systolic,
                        diastolic = bloodPressure.diastolic,
                        dateTime = bloodPressure.dateTime,
                        state = bloodPressure.state,
                        userId = bloodPressure.userId
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getBloodPressures(): Flow<List<BloodPressure>> {
        return flow {
            emit(database.getBloodPressures()?.map { it.mapToDomain() } ?: emptyList())
        }.flowOn(Dispatchers.IO)
    }

    override fun deleteBloodPressures(userId: Long): Flow<Unit> {
        return flow {
            emit(database.deleteBloodPressure(userId))
        }.flowOn(Dispatchers.IO)
    }
}