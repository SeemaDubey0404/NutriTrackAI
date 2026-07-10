package com.nutrition.nutritrackai.core.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nutrition.nutritrackai.ui.home.HomeScreen
import com.nutrition.nutritrackai.ui.insight.InsightScreen
import com.nutrition.nutritrackai.ui.meals.MealsScreen
import com.nutrition.nutritrackai.ui.meals.search.SearchFoodScreen
import com.nutrition.nutritrackai.ui.profile.ProfileScreen
import com.nutrition.nutritrackai.ui.water.WaterScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
        modifier = modifier
    ) {

        composable(NavRoutes.Home.route) {
            HomeScreen()
        }

        composable(NavRoutes.Meals.route) {
            MealsScreen()
        }

        composable(NavRoutes.Water.route) {
            WaterScreen()
        }

        composable(NavRoutes.Insights.route) {
            InsightScreen()
        }

        composable(NavRoutes.Profile.route) {
            ProfileScreen()
        }

    }

}