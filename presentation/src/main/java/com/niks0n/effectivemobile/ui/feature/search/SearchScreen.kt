package com.niks0n.effectivemobile.ui.feature.search

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.niks0n.domain.models.OfferModel
import com.niks0n.domain.models.VacancyModel
import com.niks0n.effectivemobile.R
import com.niks0n.effectivemobile.ui.theme.colorBackgroundElement
import com.niks0n.effectivemobile.ui.theme.colorSecondaryDarkGray
import com.niks0n.effectivemobile.ui.theme.customDarkGreenDark

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val iconsList = viewModel.iconsList

    SearchScreenContent(
        modifier = modifier
            .padding(WindowInsets.statusBars.asPaddingValues()),
        state = state,
        iconsList = iconsList
    )
}

@Composable
private fun SearchScreenContent(
    modifier: Modifier = Modifier,
    state: MainScreenState,
    iconsList: List<Int>
) {
    Crossfade(
        targetState = state is MainScreenState.Loading,
        label = ""
    ) { targetState ->
        when (targetState) {
            true -> LoadingSearchScreen()
            else -> {
                val successState = state as MainScreenState.Success
                SuccessScreenContent(
                    modifier = modifier,
                    offersList = successState.jobInfo.offers,
                    vacanciesList = successState.jobInfo.vacancies,
                    iconsList = iconsList
                )
            }
        }
    }
}

@Composable
fun SuccessScreenContent(
    modifier: Modifier = Modifier,
    offersList: List<OfferModel>,
    vacanciesList:  List<VacancyModel>,
    iconsList: List<Int>
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            SearchComponent()
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(offersList) { index, offerItem ->
                    OfferItem(
                        offerItem = offerItem,
                        iconId = iconsList[index]
                    )
                }
            }
        }
    }
}

@Composable
fun OfferItem(
    modifier: Modifier = Modifier,
    offerItem: OfferModel,
    iconId: Int
) {
    Column(
        modifier = modifier
            .size(width = 132.dp, height = 120.dp)
            .clip(RoundedCornerShape(cornersRoundedSize))
            .background(colorBackgroundElement)
            .padding(8.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconId),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = offerItem.title,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        )
        if (offerItem.button.text != "") {
            Text(
                modifier = Modifier.padding(top = 4.dp).clickable {},
                text = offerItem.button.text,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                ),
                color = customDarkGreenDark
            )
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
    Spacer(modifier = Modifier.padding(bottom = 16.dp))
}

@Composable
fun LoadingSearchScreen() {
    Column(modifier = Modifier.fillMaxSize()) {  }
}

val cornersRoundedSize = 10.dp