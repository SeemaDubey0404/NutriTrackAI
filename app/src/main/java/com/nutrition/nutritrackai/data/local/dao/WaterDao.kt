package com.nutrition.nutritrackai.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import com.nutrition.nutritrackai.data.local.entity.WaterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertWater(
        water: WaterEntity
    )

    @Query(
        """
    SELECT * FROM water
    WHERE timestamp >= :startOfDay
    """
    )
    fun getWater(
        startOfDay: Long
    ): Flow<List<WaterEntity>>

    @Query("SELECT * FROM water ORDER BY timestamp DESC")
    fun getAllWater(): Flow<List<WaterEntity>>

}