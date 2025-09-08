package com.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.data.models.BloodPressureDb

@Dao
interface BloodPressureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createBloodPressure(bloodPressureDb: BloodPressureDb)

    @Query("SELECT * FROM blood_pressure")
    suspend fun getAllBloodPressure(): List<BloodPressureDb>?

    @Query("SELECT * FROM blood_pressure WHERE id = :id")
    suspend fun getBloodPressure(id: Long): BloodPressureDb?

    @Update
    suspend fun updateBloodPressure(bloodPressure: BloodPressureDb)

    @Query("DELETE FROM blood_pressure WHERE id = :id")
    suspend fun deleteBloodPressure(id: Long)
}