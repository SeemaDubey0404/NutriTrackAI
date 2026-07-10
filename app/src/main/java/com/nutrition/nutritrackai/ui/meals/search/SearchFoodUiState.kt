package com.nutrition.nutritrackai.ui.meals.search


import com.nutrition.nutritrackai.data.remote.dto.FoodDto
import com.nutrition.nutritrackai.domain.model.Food

data class SearchFoodUiState(

    val query: String = "",

    val isLoading: Boolean = false,

    val foods: List<Food> = emptyList()

)