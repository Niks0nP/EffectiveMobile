package com.niks0n.data.repository

import com.niks0n.data.datasource.VacancyDataSource
import com.niks0n.domain.models.JobDataModel
import com.niks0n.domain.repository.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class VacanciesRepositoryImpl @Inject constructor(
    private val vacancyDataSource: VacancyDataSource
) : VacanciesRepository {

    override fun getVacanciesInfo(): Flow<JobDataModel> {
        return vacancyDataSource.getVacanciesInfo()
    }
}