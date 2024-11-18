package com.niks0n.effectivemobile.ui.feature.search

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.niks0n.domain.models.OfferModel
import com.niks0n.domain.models.VacancyModel
import com.niks0n.effectivemobile.R
import com.niks0n.effectivemobile.ui.theme.buttonColor
import com.niks0n.effectivemobile.ui.theme.colorBackgroundElement
import com.niks0n.effectivemobile.ui.theme.colorSecondaryDarkGray
import com.niks0n.effectivemobile.ui.theme.customGreenContainerDarkMediumContrast
import com.niks0n.effectivemobile.utils.intToMonth
import com.niks0n.effectivemobile.utils.offerIcon

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchScreenContent(
        modifier = modifier
            .padding(WindowInsets.statusBars.asPaddingValues()),
        state = state
    )
}

@Composable
private fun SearchScreenContent(
    modifier: Modifier = Modifier,
    state: MainScreenState
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
                    vacanciesList = successState.jobInfo.vacancies
                )
            }
        }
    }
}

@Composable
fun SuccessScreenContent(
    modifier: Modifier = Modifier,
    offersList: List<OfferModel>,
    vacanciesList:  List<VacancyModel>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SearchComponent()
        }
        item {
            OfferList(
                offersList = offersList
            )
        }
        item {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(R.string.header_vacancies),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        items(vacanciesList) { vacancy ->
            VacancyItem(vacancyItem = vacancy)
        }
    }
}

@Composable
private fun OfferList(
    offersList: List<OfferModel>
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(offersList) { index, offerItem ->
            OfferItem(
                offerItem = offerItem
            )
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 16.dp))
}

@Composable
private fun OfferItem(
    modifier: Modifier = Modifier,
    offerItem: OfferModel
) {
    Column(
        modifier = modifier
            .size(width = 132.dp, height = 120.dp)
            .clip(RoundedCornerShape(cornersRoundedSize))
            .background(colorBackgroundElement)
            .padding(8.dp)
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            imageVector = ImageVector.vectorResource(offerIcon(offerName = offerItem.id)),
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
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable {},
                text = offerItem.button.text,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                ),
                color = customGreenContainerDarkMediumContrast
            )
        }
    }
}

@Composable
fun VacancyItem(
    modifier: Modifier = Modifier,
    vacancyItem: VacancyModel
) {
    var selected by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .heightIn(240.dp)
            .clip(RoundedCornerShape(cornersRoundedSize))
            .background(colorBackgroundElement)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = if (vacancyItem.lookingNumber != 0) "Сейчас просматривает ${vacancyItem.lookingNumber} человек" else "",
                color = customGreenContainerDarkMediumContrast
            )
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { selected = !selected },
                imageVector = ImageVector.vectorResource(
                    if (selected) R.drawable.favorite_current
                    else R.drawable.favorite_default
                ),
                contentDescription = ""
            )
        }
        Text(
            text = vacancyItem.title,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = vacancyItem.address.town
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Text(text = vacancyItem.company)
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_company),
                contentDescription = ""
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.experience_icon),
                contentDescription = ""
            )
            Text(text = vacancyItem.experience.previewText)
        }
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Опубликовано ${vacancyItem.publishedDate.takeLast(2).toInt()} ${intToMonth(vacancyItem.publishedDate.substring(5,7).toInt())}",
            color = Color.Gray
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = buttonColor,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.White
            ),
            onClick = {}
        ) {
            Text(text = stringResource(R.string.button_vacancy))
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

@Composable
fun LoadingSearchScreen() {
    Column(modifier = Modifier.fillMaxSize()) {  }
}

val cornersRoundedSize = 10.dp