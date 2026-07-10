package com.nutrition.nutritrackai.core.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nutrition.nutritrackai.core.designsystem.components.navigation.NtBottomBar
import com.nutrition.nutritrackai.core.designsystem.components.navigation.NtBottomBarItem
import com.nutrition.nutritrackai.core.navigation.BottomNavItem

@Composable
fun BottomBar(
    navController: NavHostController
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = backStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Meals,
        BottomNavItem.Water,
        BottomNavItem.Insights,
        BottomNavItem.Profile
    )

    NtBottomBar {

        items.forEach { item ->

            NtBottomBarItem(

                icon = item.icon,

                title = item.title,

                selected = currentRoute == item.route,

              //  selectedColor = Color(0xFF22C55E),

                onClick = {

                    navController.navigate(item.route) {

                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }

                }

            )

        }

    }

}