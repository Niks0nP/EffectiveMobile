package com.niks0n.effectivemobile.ui.feature.favorite

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.niks0n.domain.models.VacancyModel
import com.niks0n.effectivemobile.R
import com.niks0n.effectivemobile.ui.feature.search.LoadingSearchScreen
import com.niks0n.effectivemobile.ui.feature.search.VacancyItem

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoriteScreenContent(
        modifier = modifier,
        state = state,
        paddingValues = paddingValues
    )




}

@Composable
private fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    state: FavoriteScreenState,
    paddingValues: PaddingValues
) {
    Crossfade(
        targetState = state is FavoriteScreenState.Loading,
        label = ""
    ) { targetState ->
        when (targetState) {
            true -> LoadingSearchScreen()
            else -> {
                val successState = state as FavoriteScreenState.Success
                SuccessFavoriteScreenContent(
                    modifier = modifier,
                    paddingValues = paddingValues,
                    vacanciesList = successState.jobInfo.vacancies
                )
            }
        }
    }
}

@Composable
fun SuccessFavoriteScreenContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
    vacanciesList: List<VacancyModel>
) {
    var items by remember { mutableStateOf(1) }

    LazyColumn (
        modifier = modifier
        .fillMaxSize()
        .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        item {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 32.dp),
                text = stringResource(R.string.header_favorites),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        item {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                text = "${items} вакансия",
                color = Color.Gray
            )
        }
        items(vacanciesList) { favoriteItem ->
            if (favoriteItem.isFavorite) {
                VacancyItem(vacancyItem = favoriteItem) { }
            }
        }
    }
}
