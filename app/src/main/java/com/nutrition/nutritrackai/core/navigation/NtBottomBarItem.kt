package com.nutrition.nutritrackai.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NtBottomBarItem(
    icon: ImageVector,
    title: String,
    selected: Boolean,
    selectedColor: Color,
    onClick: () -> Unit
) {

    val background by animateColorAsState(
        if (selected)
            selectedColor.copy(alpha = .16f)
        else
            Color.Transparent,
        label = ""
    )

    val tint by animateColorAsState(
        if (selected)
            selectedColor
        else
            MaterialTheme.colorScheme.onSurface.copy(alpha = .65f),
        label = ""
    )

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .background(background)
            .clickable { onClick() }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy
                )
            )
            .padding(
                horizontal = 14.dp,
                vertical = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = tint,
            modifier = Modifier.size(22.dp)
        )

        AnimatedVisibility(selected) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = title,
                    color = tint,
                    style = MaterialTheme.typography.labelLarge
                )

            }

        }

    }

}