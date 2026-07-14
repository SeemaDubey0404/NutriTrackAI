package com.nutrition.nutritrackai.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import com.nutrition.nutritrackai.domain.model.NutritionSummary
import com.nutrition.nutritrackai.data.remote.repository.MealRepository
import com.nutrition.nutritrackai.data.remote.repository.WaterRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MealRepository,
    private val waterRepository: WaterRepository

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

}