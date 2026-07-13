package com.nutrition.nutritrackai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(
        meal: MealEntity
    )

    @Query(
        "SELECT * FROM meals ORDER BY timestamp DESC"
    )
    fun getMeals(): Flow<List<MealEntity>>

}