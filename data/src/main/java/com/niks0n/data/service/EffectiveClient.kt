package com.niks0n.data.service

import com.niks0n.data.models.JobData
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class EffectiveClient @Inject constructor(private val service: EffectiveService) {

    /**
     * Возвращает информацию по вакансиям и интересным предложениям.
     * @return [ApiResponse] содержащий информацию о вакансиях [JobData].
     */
    suspend fun getVacanciesInfo(): ApiResponse<JobData> {
        return service.getVacanciesInfo()
    }
}