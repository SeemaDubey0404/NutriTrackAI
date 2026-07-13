package com.nutrition.nutritrackai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val imageUrl: String?,

    val calories: Double,

    val protein: Double,

    val carbs: Double,

    val fat: Double,

    val quantity: Int,

    val mealType: String,

    val timestamp: Long

)