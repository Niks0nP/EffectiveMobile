package com.niks0n.effectivemobile.navigation

import com.niks0n.effectivemobile.R
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Search : Route {
        @Serializable
        data object Home : Route

        @Serializable
        data object Detail : Route
    }

    @Serializable
    data object Favorite : Route

    @Serializable
    data object Response : Route

    @Serializable
    data object Messages : Route

    @Serializable
    data object Profile : Route
}

enum class TopLevelDestination(
    val textResId: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val route: Route
) {
    Search(
        textResId = R.string.search_bar_text,
        selectedIcon = R.drawable.search_current,
        unselectedIcon = R.drawable.search_default,
        route = Route.Search
    ),
    Favorite(
        textResId = R.string.favorite_bar_text,
        selectedIcon = R.drawable.favorite_current,
        unselectedIcon = R.drawable.favorite_default,
        route = Route.Favorite
    ),
    Response(
        textResId = R.string.response_bar_text,
        selectedIcon = R.drawable.response_current,
        unselectedIcon = R.drawable.response_default,
        route = Route.Response
    ),
    Messages(
        textResId = R.string.messages_bar_text,
        selectedIcon = R.drawable.messages_current,
        unselectedIcon = R.drawable.messages_default,
        route = Route.Messages
    ),
    Profile(
        textResId = R.string.profile_bar_text,
        selectedIcon = R.drawable.profile_current,
        unselectedIcon = R.drawable.profile_default,
        route = Route.Profile
    );
}