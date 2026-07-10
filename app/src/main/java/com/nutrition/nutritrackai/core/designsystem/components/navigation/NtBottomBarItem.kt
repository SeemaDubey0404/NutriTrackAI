package com.nutrition.nutritrackai.core.designsystem.components.navigation


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.nutrition.nutritrackai.core.designsystem.color.NtColorScheme

@Composable
fun NtBottomBarItem(
    selected: Boolean,
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {

    val background by animateColorAsState(
        if (selected)
            NtColorScheme.Emerald.copy(alpha = .18f)
        else
            Color.Transparent,
        label = ""
    )

    val tint by animateColorAsState(
        if (selected)
            NtColorScheme.Emerald
        else
            Color.White.copy(.75f),
        label = ""
    )

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(background)
            .clickable { onClick() }
            .animateContentSize(spring())
            .padding(
                horizontal = 14.dp,
                vertical = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = tint,
            modifier = Modifier.size(22.dp)
        )

        AnimatedVisibility(selected) {

            Text(
                text = title,
                color = tint,
                style = MaterialTheme.typography.labelLarge
            )

        }

    }

}