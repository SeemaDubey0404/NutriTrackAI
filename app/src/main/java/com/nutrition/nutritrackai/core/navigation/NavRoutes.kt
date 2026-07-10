package com.nutrition.nutritrackai.core.navigation



sealed class NavRoutes(val route: String) {

    data object Splash : NavRoutes("splash")

    data object Onboarding : NavRoutes("onboarding")

    data object Home : NavRoutes("home")

    data object Meals : NavRoutes("meals")

    data object Water : NavRoutes("water")

    data object Insights : NavRoutes("insights")

    data object Profile : NavRoutes("profile")
    data object SearchFood : NavRoutes("search_food")
    data object FoodDetails : NavRoutes("food_details")
}