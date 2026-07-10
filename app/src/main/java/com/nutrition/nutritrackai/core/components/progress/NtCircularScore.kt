package com.nutrition.nutritrackai.core.components.progress


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NtCircularScore(
    score: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(score) {
        progress.animateTo(
            score / 100f,
            animationSpec = tween(1200)
        )
    }

    Box(
        modifier = modifier.size(220.dp),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.matchParentSize()
        ) {

            val stroke = 8.dp.toPx()

            // Background arc (270°)
            drawArc(
                color = color.copy(alpha = 0.15f),
                startAngle = 135f,
                sweepAngle = 250f,
                useCenter = false,
                style = Stroke(
                    width = stroke,
                    cap = StrokeCap.Round
                )
            )

            // Progress arc
            drawArc(
                color = color,
                startAngle = 145f,
                sweepAngle = 270f * progress.value,
                useCenter = false,
                style = Stroke(
                    width = stroke,
                    cap = StrokeCap.Round
                )
            )
        }

        Column(
            modifier = Modifier.offset(y = (-6).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = score.toString(),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = color
            )

            Text(
                text = when {
                    score >= 90 -> "Excellent 🎉"
                    score >= 75 -> "Great 💪"
                    score >= 60 -> "Good 👍"
                    else -> "Keep Going"
                },
                style = MaterialTheme.typography.titleMedium,
                color = color
            )
        }
    }
}