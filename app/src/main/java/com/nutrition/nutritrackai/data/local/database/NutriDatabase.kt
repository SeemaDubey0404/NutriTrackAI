package com.nutrition.nutritrackai.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.nutrition.nutritrackai.data.local.dao.MealDao
import com.nutrition.nutritrackai.data.local.dao.WaterDao
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import com.nutrition.nutritrackai.data.local.entity.WaterEntity

@Database(
    entities = [
        MealEntity::class,
        WaterEntity::class
    ],

    version = 2
)
abstract class NutriDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao
    abstract fun waterDao(): WaterDao


}