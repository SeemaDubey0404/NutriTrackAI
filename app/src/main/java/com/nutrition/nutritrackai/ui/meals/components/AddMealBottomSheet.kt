package com.nutrition.nutritrackai.ui.meals.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMealBottomSheet(
    onDismiss: () -> Unit,
    onScanMeal: () -> Unit = {},
    onSearchFood: () -> Unit = {},
    onManualEntry: () -> Unit = {},
    onVoiceEntry: () -> Unit = {}
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(
            topStart = 32.dp,
            topEnd = 32.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(24.dp)
        ) {

            Text(
                text = "Add Meal",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            AddMealItem(
                emoji = "📷",
                title = "Scan Meal",
                subtitle = "Take a photo and detect food",
                onClick = onScanMeal
            )

            AddMealItem(
                emoji = "🔎",
                title = "Search Food",
                subtitle = "Search our food database",
                onClick = onSearchFood
            )

            AddMealItem(
                emoji = "✍️",
                title = "Manual Entry",
                subtitle = "Enter calories manually",
                onClick = onManualEntry
            )

            AddMealItem(
                emoji = "🎤",
                title = "Voice Entry",
                subtitle = "Describe your meal",
                onClick = onVoiceEntry
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

        }

    }

}

@Composable
private fun AddMealItem(
    emoji: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(20.dp),
        tonalElevation = 2.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        Color.LightGray.copy(alpha = .2f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = emoji
                )

            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

            }

        }

    }

}