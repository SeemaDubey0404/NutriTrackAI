package com.nutrition.nutritrackai.data.remote.repository

import com.nutrition.nutritrackai.data.remote.api.FoodApi
import com.nutrition.nutritrackai.data.remote.dto.FoodDto
import com.nutrition.nutritrackai.domain.model.Food
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodApi: FoodApi
) {

    suspend fun searchFood(
        query: String
    ): List<Food> {

        return try {
            val response = foodApi.searchFood(query)

            println(response.products.firstOrNull())

            return response.products.map {
                it.toDomain()
            }

            foodApi.searchFood(query)
                .products
                .map { it.toDomain() }

        } catch (e: Exception) {

            e.printStackTrace()

            emptyList()
        }
    }
}

private fun FoodDto.toDomain(): Food {

    return Food(

        name = product_name
            ?.takeIf { it.isNotBlank() }
            ?: "Unknown Food",

        imageUrl = image_front_small_url,

        calories = nutriments?.energyKcal100g ?: 0.0,

        protein = nutriments?.proteins100g ?: 0.0,

        carbs = nutriments?.carbohydrates100g ?: 0.0,

        fat = nutriments?.fat100g ?: 0.0
    )
}