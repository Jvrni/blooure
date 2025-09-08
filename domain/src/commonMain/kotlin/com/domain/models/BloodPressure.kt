package com.domain.models

data class BloodPressure(
    val id: Long = 0L,
    val systolic: Int,
    val diastolic: Int,
    val dateTime: String,
    val state: String,
    val userId: Long = 0L
)
