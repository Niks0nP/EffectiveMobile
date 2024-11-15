package com.niks0n.domain.models

data class VacancyModel(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: AddressModel,
    val company: String,
    val experience: ExperienceModel,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryModel,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
    val questions: List<String>
)

data class AddressModel(
    val town: String,
    val street: String,
    val house: String
)

data class ExperienceModel(
    val previewText: String,
    val text: String
)

data class SalaryModel(
    val short: String,
    val full: String
)