package com.domain.models

data class BloodPressure(
    val id: Long = 0L,
    val systolic: Int,
    val diastolic: Int,
    val date: String,
    val time: String,
    val userId: Long = 0L
)
