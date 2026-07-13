package com.nutrition.nutritrackai.ui.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nutrition.nutritrackai.core.designsystem.color.NtColorScheme
import com.nutrition.nutritrackai.core.designsystem.components.NtMetricCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.WaterDrop
import com.nutrition.nutritrackai.core.designsystem.components.NtSectionHeader
import com.nutrition.nutritrackai.core.designsystem.components.card.NtCoachCard
import com.nutrition.nutritrackai.core.designsystem.components.card.NtHeroCard
import com.nutrition.nutritrackai.core.designsystem.components.card.NtMealCard
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val meals by viewModel.meals.collectAsStateWithLifecycle()
  //  val state by viewModel.uiState.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


            item(span = { GridItemSpan(2) }) {

                Column {

                    Text(
                        text = "Good Morning 👋",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Text(
                        text = "13 July 2026",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    NtHeroCard(
                        score = 85
                    )

                }

            }



        item {

            NtMetricCard(
                title = "Calories",
                value = "1450",
                unit = "kcal",
                progress = .66f,
                icon = Icons.Default.LocalFireDepartment,
                color = Color(0xFFFF7A00)
            )

        }

        item {

            NtMetricCard(
                title = "Water",
                value = "1.8",
                unit = "L",
                progress = .60f,
                icon = Icons.Default.WaterDrop,
                color = Color(0xFF2196F3)
            )
        }

        item {

            NtMetricCard(
                title = "Protein",
                value = "82",
                unit = "g",
                progress = .68f,
                icon = Icons.Default.FitnessCenter,
                color = Color(0xFF7C4DFF)
            )

        }

        item {

            NtMetricCard(
                title = "Carbs",
                value = "145",
                unit = "g",
                progress = .72f,
                icon = Icons.Default.Grass,
                color = Color(0xFF2ECC71)
            )

        }
        item(span = { GridItemSpan(2) }) {
            NtCoachCard(
                message = "Great job! Keep tracking your meals."
            )
        }
        item(span = { GridItemSpan(2) }) {

            NtSectionHeader(
                title = "Today's Meals"
            )

        }
        items(
            meals.size,
            span = { GridItemSpan(2) }
        ) { index ->

            val meal = meals[index]

            NtMealCard(

                title = meal.name,

                foods = "${meal.mealType} • ${meal.quantity} g",

                calories = meal.calories.toInt(),

                icon = Icons.Default.LocalFireDepartment,

                completed = true

            )

        }

    }

}