package com.nutrition.nutritrackai.ui.water


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nutrition.nutritrackai.ui.home.HomeViewModel

@Composable
fun WaterScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val water by viewModel.water.collectAsStateWithLifecycle()
    val waterGoal by viewModel
        .waterGoal
        .collectAsStateWithLifecycle()
    val progress = (water / (waterGoal * 1000))
        .coerceIn(0f, 1f)
    val history by viewModel
        .waterHistory
        .collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Icon(
            imageVector = Icons.Default.WaterDrop,
            contentDescription = null,
            modifier = Modifier.size(72.dp)
        )

        Text(
            text = "Water Tracker",
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "%.1f / %.1f L".format(
                        water / 1000f,
                        waterGoal
                    ),
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth()
                )

            }

        }

        Column(

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            Text(

                text = "Quick Add",

                style = MaterialTheme.typography.titleMedium

            )
            Text(
                text = "Daily Goal",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                GoalButton(
                    text = "2 L",
                    onClick = {
                        viewModel.setWaterGoal(2f)
                    }
                )

                GoalButton(
                    text = "2.5 L",
                    onClick = {
                        viewModel.setWaterGoal(2.5f)
                    }
                )

                GoalButton(
                    text = "3 L",
                    onClick = {
                        viewModel.setWaterGoal(3f)
                    }
                )

                GoalButton(
                    text = "4 L",
                    onClick = {
                        viewModel.setWaterGoal(4f)
                    }
                )
            }
            Text(
                text = "Today's History",
                style = MaterialTheme.typography.titleMedium
            )

            history.forEach { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "💧 ${item.amount} ml"
                        )

                        Text(
                            text = java.text.SimpleDateFormat(
                                "hh:mm a",
                                java.util.Locale.getDefault()
                            ).format(
                                java.util.Date(item.timestamp)
                            )
                        )

                    }

                }

            }
            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                WaterButton(

                    text = "+100 ml",

                    onClick = {

                        viewModel.addWater(100)

                    }

                )

                WaterButton(

                    text = "+250 ml",

                    onClick = {

                        viewModel.addWater(250)

                    }

                )

                WaterButton(

                    text = "+500 ml",

                    onClick = {

                        viewModel.addWater(500)

                    }

                )

            }

        }

    }

}

@Composable
fun WaterButton(

    text: String,

    onClick: () -> Unit

) {

    Button(

        onClick = onClick

    ) {

        Text(text)

    }

}
@Composable
fun GoalButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick
    ) {
        Text(text)
    }
}