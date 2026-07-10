package com.nutrition.nutritrackai.core.navigation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NtBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 18.dp,
                vertical = 12.dp
            ),
        contentAlignment = Alignment.BottomCenter
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(38.dp),
                    clip = false
                ),
            shape = RoundedCornerShape(38.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = .92f),
            tonalElevation = 0.dp
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(74.dp)
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )

        }

    }

}