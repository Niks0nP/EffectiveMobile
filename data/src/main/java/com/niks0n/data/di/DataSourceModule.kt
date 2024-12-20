package com.niks0n.data.di

import com.niks0n.data.datasource.VacancyDataSource
import com.niks0n.data.service.EffectiveClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideVacancyDataSource(client: EffectiveClient): VacancyDataSource {
        return VacancyDataSource(client)
    }
}