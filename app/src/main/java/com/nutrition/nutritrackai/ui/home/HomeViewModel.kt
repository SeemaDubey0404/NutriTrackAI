package com.nutrition.nutritrackai.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import com.nutrition.nutritrackai.data.preferences.UserPreferences
import com.nutrition.nutritrackai.domain.model.NutritionSummary
import com.nutrition.nutritrackai.data.remote.repository.MealRepository
import com.nutrition.nutritrackai.data.remote.repository.WaterRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MealRepository,
    private val waterRepository: WaterRepository,
    private val userPreferences: UserPreferences

) : ViewModel() {

    private val _meals = MutableStateFlow<List<MealEntity>>(
        emptyList()
    )

    val meals = _meals.asStateFlow()
    private val _nutritionSummary =
        MutableStateFlow(
            NutritionSummary()
        )

    val nutritionSummary =
        _nutritionSummary.asStateFlow()
    private val _water = MutableStateFlow(0)

    val water = _water.asStateFlow()
    val waterHistory =
        waterRepository
            .getWaterHistory()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    private val _waterGoal = MutableStateFlow(3f)

    val waterGoal = _waterGoal.asStateFlow()
    init {

        viewModelScope.launch {

            repository.getMeals().collect {

                _meals.value = it

                _nutritionSummary.value = NutritionSummary(

                    calories = it.sumOf { meal ->
                        meal.calories.toInt()
                    },

                    protein = it.sumOf { meal ->
                        meal.protein.toInt()
                    },

                    carbs = it.sumOf { meal ->
                        meal.carbs.toInt()
                    },

                    fat = it.sumOf { meal ->
                        meal.fat.toInt()
                    }

                )

            }

        }
        viewModelScope.launch {

            waterRepository
                .getWater()
                .collect { waterList ->

                    _water.value = waterList.sumOf {
                        it.amount
                    }

                }

        }
        viewModelScope.launch {

            userPreferences
                .waterGoal
                .collect {

                    _waterGoal.value = it

                }

        }

    }
    fun deleteMeal(
        meal: MealEntity
    ) {

        viewModelScope.launch {

            repository.deleteMeal(meal)

        }

    }
    fun addWater(

        amount: Int

    ) {

        viewModelScope.launch {

            waterRepository.addWater(
                amount = amount
            )

        }

    }
    fun setWaterGoal(
        liters: Float
    ) {

        viewModelScope.launch {

            userPreferences
                .saveWaterGoal(
                    liters
                )

        }

    }
    fun getCoachMessage(): String {

        return when {

            water.value >= waterGoal.value * 1000 * 0.75f -> {

                "🎉 Awesome! You reached your water goal."

            }

            water.value >= waterGoal.value * 1000 * 0.75f -> {

                "💧 Almost there! Drink a little more water."

            }

            nutritionSummary.value.calories < 500 -> {

                "🍽️ Don't forget to log your meals."

            }

            nutritionSummary.value.protein < 50 -> {

                "🥩 Your protein intake is low today."

            }

            else -> {

                "🔥 Great job! Keep tracking your meals."

            }

        }

    }
    fun getHealthScore(): Int {

        val calorieScore =
            (nutritionSummary.value.calories / 2200f)
                .coerceIn(0f, 1f)

        val waterScore =
            (water.value / (waterGoal.value * 1000))
                .coerceIn(0f, 1f)

        val proteinScore =
            (nutritionSummary.value.protein / 120f)
                .coerceIn(0f, 1f)

        val carbScore =
            (nutritionSummary.value.carbs / 250f)
                .coerceIn(0f, 1f)

        val totalScore = (
                calorieScore * 30 +
                        waterScore * 30 +
                        proteinScore * 20 +
                        carbScore * 20
                )

        return totalScore.toInt()
    }
    fun getHealthMessage(): String {

        val score = getHealthScore()

        return when {

            score < 30 -> {

                "Needs improvement 😴"

            }

            score < 60 -> {

                "Keep going 👍"

            }

            score < 80 -> {

                "Great 💪"

            }

            else -> {

                "Excellent 🔥"

            }

        }

    }
}