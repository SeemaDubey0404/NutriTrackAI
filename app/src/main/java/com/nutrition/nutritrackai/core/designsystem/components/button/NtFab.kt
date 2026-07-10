package com.nutrition.nutritrackai.core.designsystem.components.button


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NtFab(
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(64.dp)
            .shadow(
                elevation = 20.dp,
                shape = CircleShape
            )
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF22C55E),
                        Color(0xFF2563EB)
                    )
                ),
                shape = CircleShape
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = Color.White
        )

    }
}