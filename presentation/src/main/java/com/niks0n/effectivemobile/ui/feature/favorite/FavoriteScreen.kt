package com.niks0n.effectivemobile.ui.feature.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niks0n.effectivemobile.R

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    LazyColumn (modifier = modifier
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
    }
}