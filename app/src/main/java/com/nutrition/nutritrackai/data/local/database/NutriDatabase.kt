package com.nutrition.nutritrackai.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.nutrition.nutritrackai.data.local.dao.MealDao
import com.nutrition.nutritrackai.data.local.entity.MealEntity

@Database(
    entities = [
        MealEntity::class
    ],
    version = 1
)
abstract class NutriDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao

}