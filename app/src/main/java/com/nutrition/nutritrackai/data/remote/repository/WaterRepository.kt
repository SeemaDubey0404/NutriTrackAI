package com.nutrition.nutritrackai.data.remote.repository

import com.nutrition.nutritrackai.core.util.startOfToday
import com.nutrition.nutritrackai.data.local.dao.WaterDao
import com.nutrition.nutritrackai.data.local.entity.WaterEntity
import javax.inject.Inject

class WaterRepository @Inject constructor(

    private val waterDao: WaterDao

) {

    suspend fun addWater(
        amount: Int
    ) {

        waterDao.insertWater(

            WaterEntity(
                amount = amount,
                timestamp = System.currentTimeMillis()
            )

        )

    }

    fun getWater() =
        waterDao.getWater(
            startOfToday()
        )

}