package com.nutrition.nutritrackai.data.remote.repository

import com.nutrition.nutritrackai.core.util.startOfToday
import com.nutrition.nutritrackai.data.local.dao.MealDao
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val mealDao: MealDao
) {

    suspend fun insertMeal(
        meal: MealEntity
    ) {
        println("Saving meal: ${meal.name}")
        mealDao.insertMeal(meal)

    }

    fun getMeals() =
        mealDao.getMeals(
            startOfToday()
        )
    suspend fun deleteMeal(
        meal: MealEntity
    ) {

        mealDao.deleteMeal(meal)

    }

}