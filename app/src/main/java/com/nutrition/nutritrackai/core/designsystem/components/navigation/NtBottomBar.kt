package com.nutrition.nutritrackai.core.designsystem.components.navigation


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
                .height(78.dp)
                .shadow(
                    elevation = 24.dp,
                    shape = RoundedCornerShape(38.dp)
                ),
            shape = RoundedCornerShape(38.dp),
            color = Color(0xFF1D1D22).copy(alpha = .88f),
            tonalElevation = 0.dp
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )

        }

    }

}