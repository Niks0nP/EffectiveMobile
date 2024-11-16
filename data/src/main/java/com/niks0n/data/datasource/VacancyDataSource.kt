package com.niks0n.data.datasource

import com.niks0n.data.mapper.toJobDataModel
import com.niks0n.data.service.EffectiveClient
import com.niks0n.domain.models.JobDataModel
import com.niks0n.domain.repository.VacanciesRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VacancyDataSource @Inject constructor(
    private val client: EffectiveClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : VacanciesRepository {

    override fun getVacanciesInfo(): Flow<JobDataModel> = flow {
        try {
            val response = client.getVacanciesInfo()

            when(response) {
                is ApiResponse.Success -> {
                    val jobData = response.data
                    val jobDataModel = jobData.toJobDataModel()
                    emit(jobDataModel)
                }
                is ApiResponse.Failure -> {
                    throw Exception(response.message())
                }
            }
        }
        catch (e: Exception) {
            throw Exception(e.message)
        }
    }.flowOn(dispatcher)
}