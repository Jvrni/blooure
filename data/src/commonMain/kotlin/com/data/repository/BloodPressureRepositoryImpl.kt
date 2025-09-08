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

class BloodPressureRepositoryImpl(val database: BloodPressureDao) : BloodPressureRepository {
    override fun addBloodPressure(bloodPressure: BloodPressure): Flow<Unit> {
        return flow {
            emit(
                database.createBloodPressure(
                    BloodPressureDb(
                        id = bloodPressure.id,
                        systolic = bloodPressure.systolic,
                        diastolic = bloodPressure.diastolic,
                        time = bloodPressure.time,
                        date = bloodPressure.date,
                        userId = bloodPressure.userId
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}