package com.niks0n.effectivemobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.niks0n.effectivemobile.navigation.Route
import com.niks0n.effectivemobile.navigation.TopLevelDestination
import com.niks0n.effectivemobile.navigation.hasRoute
import com.niks0n.effectivemobile.ui.theme.primaryLight

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    onNavigate: (Route) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.hasRoute(destination.route)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(destination.route) },
                icon = {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                if (selected) destination.selectedIcon
                                else destination.unselectedIcon),
                            contentDescription = ""
                        )

                    }
                },
                label = {
                    Text(
                        text = stringResource(destination.textResId),
                        color = if (selected) primaryLight else Color.LightGray
                    )
                }
            )
        }
    }
}