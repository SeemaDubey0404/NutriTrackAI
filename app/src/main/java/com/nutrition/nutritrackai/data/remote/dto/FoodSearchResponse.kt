package com.nutrition.nutritrackai.data.remote.dto

import com.google.gson.annotations.SerializedName


data class FoodSearchResponse(

    val products: List<FoodDto>

)

data class FoodDto(

    val product_name: String?,

    val image_front_small_url: String?,

    val nutriments: Nutriments?

)


data class Nutriments(

    @SerializedName("energy-kcal_100g")
    val energyKcal100g: Double?,

    @SerializedName("proteins_100g")
    val proteins100g: Double?,

    @SerializedName("carbohydrates_100g")
    val carbohydrates100g: Double?,

    @SerializedName("fat_100g")
    val fat100g: Double?
)