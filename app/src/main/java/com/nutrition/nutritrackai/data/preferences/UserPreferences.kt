package com.nutrition.nutritrackai.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "user_preferences"
)

class UserPreferences(
    private val context: Context
) {

    companion object {

        val WATER_GOAL = floatPreferencesKey(
            "water_goal"
        )

    }

    val waterGoal = context.dataStore.data.map {

        it[WATER_GOAL] ?: 3f

    }

    suspend fun saveWaterGoal(
        liters: Float
    ) {

        context.dataStore.edit {

            it[WATER_GOAL] = liters

        }

    }

}