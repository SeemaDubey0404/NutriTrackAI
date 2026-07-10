package com.nutrition.nutritrackai.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.LunchDining
import com.nutrition.nutritrackai.ui.home.model.HomeMeal


data class HomeUiState(
    val greeting: String = "Good Morning",
    val date: String = "Friday, July 3",
    val healthScore: Int = 92,

    val caloriesConsumed: Int = 1450,
    val caloriesGoal: Int = 2200,

    val waterConsumed: Float = 1.8f,
    val waterGoal: Float = 3f,

    val protein: Int = 82,
    val carbs: Int = 145,
    val fat: Int = 42,
    val fiber: Int = 24,

    val aiMessage: String =
        "You're doing great. Drink another 500ml before lunch.",

    val meals: List<HomeMeal> = listOf(
        HomeMeal(
            title = "Breakfast",
            foods = "Oatmeal • Banana • Coffee",
            calories = 450,
            icon = Icons.Default.FreeBreakfast,
            completed = true
        ),
        HomeMeal(
            title = "Lunch",
            foods = "Chicken • Rice • Salad",
            calories = 620,
            icon = Icons.Default.LunchDining,
            completed = false
        ),
        HomeMeal(
            title = "Dinner",
            foods = "Not logged yet",
            calories = 0,
            icon = Icons.Default.DinnerDining,
            completed = false
        )
    )
)