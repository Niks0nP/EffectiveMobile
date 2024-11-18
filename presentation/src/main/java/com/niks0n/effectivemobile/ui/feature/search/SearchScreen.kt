package com.niks0n.effectivemobile.ui.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.niks0n.effectivemobile.R
import com.niks0n.effectivemobile.ui.theme.colorSecondaryDarkGray

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreenContent(
        modifier = modifier
            .padding(WindowInsets.statusBars.asPaddingValues()),
        state = state
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: MainScreenState
) {
    LazyColumn(modifier = modifier.background(Color.Black)) {
        item {
            SearchComponent()
        }
    }
}

@Composable
fun SearchComponent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(cornersRoundedSize))
                .background(colorSecondaryDarkGray)
        ) {
            Icon(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                imageVector = ImageVector.vectorResource(R.drawable.search_default),
                tint = Color.Gray,
                contentDescription = ""
            )
            Text(
                text = stringResource(R.string.search_component_text),
                color = Color.Gray
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(cornersRoundedSize))
                .background(colorSecondaryDarkGray)
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = ImageVector.vectorResource(R.drawable.filter_icon),
                tint = Color.Gray,
                contentDescription = ""
            )
        }
    }
}

val cornersRoundedSize = 10.dp