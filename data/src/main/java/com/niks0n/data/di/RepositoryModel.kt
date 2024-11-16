package com.niks0n.data.di

import com.niks0n.data.datasource.VacancyDataSource
import com.niks0n.data.repository.VacanciesRepositoryImpl
import com.niks0n.domain.repository.VacanciesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModel {
    @Provides
    @Singleton
    fun provideVacanciesRepository(vacancyDataSource: VacancyDataSource): VacanciesRepository {
        return VacanciesRepositoryImpl(vacancyDataSource)
    }
}