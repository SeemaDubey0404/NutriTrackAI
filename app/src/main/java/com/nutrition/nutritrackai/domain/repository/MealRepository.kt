package com.nutrition.nutritrackai.domain.repository


import com.nutrition.nutritrackai.data.local.dao.MealDao
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow
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

    fun getMeals(): Flow<List<MealEntity>> {

        return mealDao.getMeals()

    }

}