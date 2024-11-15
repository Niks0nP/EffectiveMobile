package com.niks0n.domain.repository

import com.niks0n.domain.models.JobDataModel
import kotlinx.coroutines.flow.Flow

interface VacanciesRepository {
    fun getVacanciesInfo(): Flow<JobDataModel>
}