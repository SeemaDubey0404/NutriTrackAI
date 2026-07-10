package com.nutrition.nutritrackai.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nutrition.nutritrackai.core.components.BottomBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import com.nutrition.nutritrackai.core.designsystem.components.navigation.buttons.NtFab
import com.nutrition.nutritrackai.core.navigation.BottomNavGraph
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.nutrition.nutritrackai.core.navigation.NavRoutes
import com.nutrition.nutritrackai.ui.meals.components.AddMealBottomSheet
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainScreen( navController: NavHostController) {
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    val bottomNavController = rememberNavController()
    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route

    Scaffold(

        modifier = Modifier.fillMaxSize(),

        floatingActionButton = {

            if (currentRoute != NavRoutes.SearchFood.route) {

                NtFab(
                    onClick = {
                        showBottomSheet = true
                    }
                )

            }

        },

        floatingActionButtonPosition = FabPosition.Center,

        bottomBar = {

            if (currentRoute != NavRoutes.SearchFood.route) {

                BottomBar(bottomNavController)

            }

        }

    ) { innerPadding ->

        BottomNavGraph(
            navController = bottomNavController,
            modifier = Modifier.padding(innerPadding)
        )

    }
    if (showBottomSheet) {

        AddMealBottomSheet(

            onDismiss = {
                showBottomSheet = false
            },

            onSearchFood = {

                showBottomSheet = false

                navController.navigate(
                    NavRoutes.SearchFood.route
                )

            }

        )

    }

}