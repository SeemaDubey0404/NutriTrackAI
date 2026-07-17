package com.nutrition.nutritrackai.ui.insight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import com.nutrition.nutritrackai.data.local.entity.WaterEntity
import com.nutrition.nutritrackai.data.remote.repository.MealRepository
import com.nutrition.nutritrackai.data.remote.repository.WaterRepository
import com.nutrition.nutritrackai.ui.insight.WeeklyStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class InsightViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val waterRepository: WaterRepository
) : ViewModel() {
    private val _weeklyStats = MutableStateFlow<List<WeeklyStats>>(emptyList())
    val weeklyStats = _weeklyStats.asStateFlow()
    private val _streak = MutableStateFlow(0)
    val streak = _streak.asStateFlow()
    init {
        loadWeeklyStats()
    }

    private fun loadWeeklyStats() {

        viewModelScope.launch {

            combine(
                mealRepository.getAllMeals(),
                waterRepository.getAllWater()
            ) { meals, water ->

                val calendar = Calendar.getInstance()

                val stats = (6 downTo 0).map { daysAgo ->

                    calendar.timeInMillis = System.currentTimeMillis()
                    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

                    val dayStart = calendar.apply {
                        set(Calendar.HOUR_OF_DAY, 0)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }.timeInMillis

                    val dayEnd = dayStart + 24 * 60 * 60 * 1000

                    WeeklyStats(
                        day = SimpleDateFormat(
                            "EEE",
                            Locale.getDefault()
                        ).format(Date(dayStart)),

                        calories = meals
                            .filter {
                                it.timestamp in dayStart until dayEnd
                            }
                            .sumOf {
                                it.calories
                            }
                            .toInt(),

                        water = water
                            .filter {
                                it.timestamp in dayStart until dayEnd
                            }
                            .sumOf {
                                it.amount
                            }
                    )
                }

                Triple(
                    stats,
                    meals,
                    water
                )

            }.collect { (stats, meals, water) ->

                _weeklyStats.value = stats

                _streak.value = calculateStreak(
                    meals,
                    water
                )
            }
        }
    }

    private fun calculateStreak(
        meals: List<MealEntity>,
        water: List<WaterEntity>
    ): Int {

        val activeDays = mutableSetOf<String>()

        val formatter = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )

        meals.forEach {

            activeDays.add(
                formatter.format(
                    Date(it.timestamp)
                )
            )

        }

        water.forEach {

            activeDays.add(
                formatter.format(
                    Date(it.timestamp)
                )
            )

        }

        var streak = 0

        val calendar = Calendar.getInstance()

        while (true) {

            val day = formatter.format(
                calendar.time
            )

            if (day in activeDays) {

                streak++

                calendar.add(
                    Calendar.DAY_OF_YEAR,
                    -1
                )

            } else {

                break

            }

        }

        return streak

    }

    fun getAchievements(
        streak: Int,
        waterGoalReached: Boolean
    ): List<Achievement> {

        return listOf(

            Achievement(
                title = "First Meal Logged",
                emoji = "🍽️",
                unlocked = true
            ),

            Achievement(
                title = "3-Day Streak",
                emoji = "🔥",
                unlocked = streak >= 3
            ),

            Achievement(
                title = "7-Day Streak",
                emoji = "🏅",
                unlocked = streak >= 7
            ),

            Achievement(
                title = "Hydration Master",
                emoji = "💧",
                unlocked = waterGoalReached
            )
        )
    }
}