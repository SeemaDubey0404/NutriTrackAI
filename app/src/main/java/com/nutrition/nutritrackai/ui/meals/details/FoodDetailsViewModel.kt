package com.nutrition.nutritrackai.ui.meals.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import com.nutrition.nutritrackai.domain.model.Food
import com.nutrition.nutritrackai.data.remote.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    fun saveMeal(
        food: Food,
        quantity: Int,
        mealType: String
    ) {

        viewModelScope.launch {

            repository.insertMeal(

                MealEntity(

                    name = food.name,

                    imageUrl = food.imageUrl,

                    calories = food.calories * quantity / 100,

                    protein = food.protein * quantity / 100,

                    carbs = food.carbs * quantity / 100,

                    fat = food.fat * quantity / 100,

                    quantity = quantity,

                    mealType = mealType,

                    timestamp = System.currentTimeMillis()

                )

            )

        }

    }

}