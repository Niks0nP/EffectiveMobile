package com.niks0n.effectivemobile.ui.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Elem(
        state = state
    )

}

@Composable
fun Elem(
    modifier: Modifier = Modifier,
    state: MainScreenState
) {

}