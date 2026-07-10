package com.nutrition.nutritrackai.domain.model


data class Food(

    val name: String,

    val imageUrl: String?,

    val calories: Double,

    val protein: Double,

    val carbs: Double,

    val fat: Double

)