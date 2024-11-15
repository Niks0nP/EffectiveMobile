package com.niks0n.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobData(
    @SerialName("offers") val offers: List<Offer>,
    @SerialName("vacancies") val vacancies: List<Vacancy>
)
