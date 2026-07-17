package com.nutrition.nutritrackai.ui.insight


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun InsightScreen(
    viewModel: InsightViewModel = hiltViewModel()
) {
    val streak by viewModel.streak.collectAsState()
    val weeklyStats by viewModel.weeklyStats.collectAsState()
    val achievements = viewModel.getAchievements(
        streak = streak,
        waterGoalReached = false
    )
    val avgCalories = if (weeklyStats.isNotEmpty()) {
        weeklyStats.sumOf { it.calories } / weeklyStats.size
    } else {
        0
    }

    val avgWater = if (weeklyStats.isNotEmpty()) {
        weeklyStats.sumOf { it.water } / weeklyStats.size
    } else {
        0
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Text(
                text = "Weekly Insights",
                style = MaterialTheme.typography.headlineMedium
            )

        }
        item {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "🔥 Current Streak",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "$streak days",
                        style = MaterialTheme.typography.headlineMedium
                    )

                }

            }

        }
        item {

            Text(
                text = "🏅 Achievements",
                style = MaterialTheme.typography.titleLarge
            )

        }
        items(achievements) { achievement ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "${achievement.emoji} ${achievement.title}"
                    )

                    Text(
                        text = if (achievement.unlocked) "✅" else "🔒"
                    )

                }

            }

        }
        item {

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                SummaryCard(
                    modifier = Modifier.weight(1f),
                    title = "Avg Calories",
                    value = "$avgCalories kcal"
                )

                SummaryCard(
                    modifier = Modifier.weight(1f),
                    title = "Avg Water",
                    value = "$avgWater ml"
                )

            }

        }

        item {

            Text(
                text = "Calories This Week",
                style = MaterialTheme.typography.titleLarge
            )

        }

        item {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(weeklyStats) { stat ->

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .width(28.dp)
                                .height((stat.calories / 20)
                                    .coerceAtLeast(10)
                                    .dp)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    RoundedCornerShape(12.dp)
                                )
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = stat.day
                        )

                    }

                }

            }

        }

        item {

            Text(
                text = "Daily Details",
                style = MaterialTheme.typography.titleLarge
            )

        }

        items(weeklyStats) { stat ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = stat.day,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Calories: ${stat.calories} kcal"
                    )

                    Text(
                        text = "Water: ${stat.water} ml"
                    )

                }

            }

        }

    }

}

@Composable
private fun SummaryCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge
            )

        }

    }

}