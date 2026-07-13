package com.nutrition.nutritrackai.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nutrition.nutritrackai.ui.main.MainScreen
import com.nutrition.nutritrackai.ui.meals.details.FoodDetailsScreen
import com.nutrition.nutritrackai.ui.meals.search.SearchFoodScreen
import com.nutrition.nutritrackai.ui.onboarding.OnboardingScreen
import com.nutrition.nutritrackai.ui.splash.SplashScreen


@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {

        composable(NavRoutes.Splash.route) {

            SplashScreen(
                onNavigate = {

                    navController.navigate(NavRoutes.Home.route) {

                        popUpTo(NavRoutes.Splash.route) {
                            inclusive = true
                        }

                    }

                }
            )

        }

        composable(NavRoutes.Onboarding.route) {

            OnboardingScreen()

        }

        composable(NavRoutes.Home.route) {

            MainScreen(
                navController = navController
            )

        }
        composable(
            route = NavRoutes.SearchFood.route
        ) {

            SearchFoodScreen(navController = navController)

        }
        composable(
            route = NavRoutes.FoodDetails.route
        ) {

            FoodDetailsScreen( navController = navController)

        }
    }

}
