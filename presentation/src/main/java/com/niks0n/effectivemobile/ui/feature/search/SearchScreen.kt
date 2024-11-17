package com.niks0n.effectivemobile.ui.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.niks0n.effectivemobile.ui.theme.onPrimaryContainerDark
import com.niks0n.effectivemobile.ui.theme.primaryContainerDark
import com.niks0n.effectivemobile.ui.theme.primaryDark
import com.niks0n.effectivemobile.ui.theme.primaryLight

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: MainScreenState
) {
    var textInput by remember { mutableStateOf("") }

    LazyColumn {
        item {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                SearchBar(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(40.dp)
                        .padding(start = 16.dp, end = 16.dp),
                    inputField = {
                        SearchBarDefaults.InputField(
                            query = textInput,
                            onQueryChange = { textInput = it },
                            expanded = false,
                            onExpandedChange = {},
                            onSearch = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    contentDescription = "",
                                )
                            }
                        )
                    },
                    expanded = false,
                    onExpandedChange = {},
                    windowInsets = WindowInsets(0.dp),
                ) {}
                IconButton(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(40.dp),
                    onClick = {},
                    colors = IconButtonColors(
                        contentColor = primaryContainerDark,
                        containerColor = primaryLight,
                        disabledContentColor = primaryDark,
                        disabledContainerColor = onPrimaryContainerDark)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    SearchScreen()
}