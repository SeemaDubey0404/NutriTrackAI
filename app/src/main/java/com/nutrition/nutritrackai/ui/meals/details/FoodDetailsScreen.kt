package com.nutrition.nutritrackai.ui.meals.details


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nutrition.nutritrackai.domain.model.Food

@Composable
fun FoodDetailsScreen(navController: NavHostController,
                      viewModel: FoodDetailsViewModel = hiltViewModel()) {
    val food = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<Food>("food")
    var quantity by remember {
        mutableFloatStateOf(100f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Food Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = food?.name ?: "Unknown Food"
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Calories: ${food?.calories ?: 0.0} "
                )

                Text(
                    text = "Protein: ${food?.protein ?: 0.0} g"
                )

                Text(

                    text = "Carbs: ${food?.carbs ?: 0.0} g"
                )

                Text(
                    text = "Fat:  ${food?.fat ?: 0.0} g"

                )

            }

        }

        Text(
            text = "Quantity: ${quantity.toInt()} g"
        )

        Slider(
            value = quantity,
            onValueChange = {
                quantity = it
            },
            valueRange = 50f..500f
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                food?.let {

                    viewModel.saveMeal(
                        food = it,
                        quantity = quantity.toInt()
                    )

                    navController.popBackStack()

                }

            }
        ) {

            Text(
                text = "Add Meal"
            )

        }

    }

}