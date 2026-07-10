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

@Composable
fun FoodDetailsScreen() {

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
                    text = "Chocolate"
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Calories: 540 kcal"
                )

                Text(
                    text = "Protein: 8 g"
                )

                Text(
                    text = "Carbs: 62 g"
                )

                Text(
                    text = "Fat: 24 g"
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

                // save meal later

            }
        ) {

            Text(
                text = "Add Meal"
            )

        }

    }

}