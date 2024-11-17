package com.niks0n.effectivemobile.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.niks0n.effectivemobile.navigation.EffectiveNavGraph
import com.niks0n.effectivemobile.navigation.TopLevelDestination
import com.niks0n.effectivemobile.ui.components.BottomNavBar

@Composable
fun EffectiveApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val topLevelDestinations = TopLevelDestination.entries

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                destinations = topLevelDestinations,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navController = navController
            )
        }
    ) { innerPadding ->
        EffectiveNavGraph(
            navController = navController,
            paddingValues = innerPadding
        )
    }

}