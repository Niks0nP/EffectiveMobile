package com.niks0n.data.service

import com.niks0n.data.models.JobData
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface EffectiveService {

    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getVacanciesInfo(): ApiResponse<JobData>

    companion object {
        const val BASE_URL: String = "https://drive.usercontent.google.com/u/0/"
    }
}