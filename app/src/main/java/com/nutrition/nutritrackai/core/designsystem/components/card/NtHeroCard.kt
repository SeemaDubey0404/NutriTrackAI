package com.nutrition.nutritrackai.core.designsystem.components.card

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nutrition.nutritrackai.core.components.progress.NtCircularScore

@Composable
fun NtHeroCard(
    score: Int,
    message: String,
    modifier: Modifier = Modifier
) {

    val progress = remember { Animatable(0f) }

    LaunchedEffect(score) {
        progress.animateTo(
            score / 100f,
            animationSpec = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(360.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF22C55E),
                        Color(0xFF3B82F6)
                    )
                ),
                shape = RoundedCornerShape(28.dp)
            )
            .padding(24.dp)
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Daily Health Score",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = .85f)
            )

            Spacer(Modifier.padding(top = 15.dp))

            NtCircularScore(
                score = score,
                color = Color.White
            )

        }

    }
}