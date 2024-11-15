package com.niks0n.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    @SerialName("id") val id: String,
    @SerialName("lookingNumber") val lookingNumber: Int? = null,
    @SerialName("title") val title: String,
    @SerialName("address") val address: Address,
    @SerialName("company") val company: String,
    @SerialName("experience") val experience: Experience,
    @SerialName("publishedDate") val publishedDate: String,
    @SerialName("isFavorite") val isFavorite: Boolean,
    @SerialName("salary") val salary: Salary,
    @SerialName("schedules") val schedules: List<String>,
    @SerialName("appliedNumber") val appliedNumber: Int? = null,
    @SerialName("description") val description: String,
    @SerialName("responsibilities") val responsibilities: String,
    @SerialName("questions") val questions: List<String>
)

@Serializable
data class Address(
    @SerialName("town") val town: String,
    @SerialName("street") val street: String,
    @SerialName("house") val house: String
)

@Serializable
data class Experience(
    @SerialName("previewText") val previewText: String,
    @SerialName("text") val text: String
)

@Serializable
data class Salary(
    @SerialName("short") val short: String? = null,
    @SerialName("full") val full: String
)