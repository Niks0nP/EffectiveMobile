package com.niks0n.effectivemobile.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.niks0n.effectivemobile.ui.feature.favorite.FavoriteScreen
import com.niks0n.effectivemobile.ui.feature.messages.MessagesScreen
import com.niks0n.effectivemobile.ui.feature.profile.ProfileScreen
import com.niks0n.effectivemobile.ui.feature.response.ResponseScreen
import com.niks0n.effectivemobile.ui.feature.search.SearchScreen

@Composable
fun EffectiveNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    startDestination: Route = Route.Search
) {

    NavHost(
        modifier = modifier
            .fillMaxSize()
            .background(containerColor),
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Route.Search> {
            SearchScreen()
        }
        composable<Route.Favorite> {
            FavoriteScreen()
        }
        composable<Route.Messages> {
            MessagesScreen()
        }
        composable<Route.Response> {
            ResponseScreen()
        }
        composable<Route.Profile> {
            ProfileScreen()
        }
    }
}

fun NavDestination?.hasRoute(route: Route) =
    this?.hierarchy?.any { it.hasRoute(route::class) } == true