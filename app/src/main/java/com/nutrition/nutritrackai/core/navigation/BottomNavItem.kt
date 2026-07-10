package com.nutrition.nutritrackai.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Analytics


sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    data object Home : BottomNavItem(
        NavRoutes.Home.route,
        "Home",
        Icons.Default.Home
    )

    data object Meals : BottomNavItem(
        NavRoutes.Meals.route,
        "Meals",
        Icons.Default.Restaurant
    )

    data object Water : BottomNavItem(
        NavRoutes.Water.route,
        "Water",
        Icons.Default.WaterDrop
    )

    data object Insights : BottomNavItem(
        NavRoutes.Insights.route,
        "Insights",
        Icons.Default.Analytics
    )

    data object Profile : BottomNavItem(
        NavRoutes.Profile.route,
        "Profile",
        Icons.Default.Person
    )
}