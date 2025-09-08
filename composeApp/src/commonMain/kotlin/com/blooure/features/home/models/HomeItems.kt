package com.blooure.features.home.models

import com.domain.models.BloodPressure
import com.domain.models.User

data class HomeItems(
    val user: User,
    val bloodPressure: BloodPressure
)
