package com.nutrition.nutritrackai.ui.home.model


import androidx.compose.ui.graphics.vector.ImageVector

data class HomeMeal(
    val title: String,
    val foods: String,
    val calories: Int,
    val icon: ImageVector,
    val completed: Boolean
)