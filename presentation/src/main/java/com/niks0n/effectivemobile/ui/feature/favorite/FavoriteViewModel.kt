package com.niks0n.effectivemobile.ui.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niks0n.domain.models.JobDataModel
import com.niks0n.domain.repository.VacanciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    repository: VacanciesRepository
) : ViewModel() {
    val state: StateFlow<FavoriteScreenState> = repository.getVacanciesInfo().map { jobInfo ->
        FavoriteScreenState.Success(
            jobInfo = jobInfo
        )
    }.catch { error ->
        throw Exception(error.message)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = FavoriteScreenState.Loading
    )
}

sealed interface FavoriteScreenState {
    data object Loading : FavoriteScreenState
    data class Success(
        val jobInfo: JobDataModel
    ) : FavoriteScreenState
}
