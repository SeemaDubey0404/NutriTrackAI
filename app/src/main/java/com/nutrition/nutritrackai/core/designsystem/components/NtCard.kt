package com.nutrition.nutritrackai.core.designsystem.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nutrition.nutritrackai.core.designsystem.color.NtColorScheme
import com.nutrition.nutritrackai.core.designsystem.shapes.NtShape
import com.nutrition.nutritrackai.core.designsystem.spacing.NtSpacing

@Composable
fun NtCard(
    modifier: Modifier = Modifier,
    background: Color = NtColorScheme.Surface,
    contentPadding: PaddingValues = PaddingValues(NtSpacing.Large),
    content: @Composable () -> Unit
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = NtShape.Large,
        colors = CardDefaults.cardColors(
            containerColor = background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = NtSpacing.ExtraSmall
        )
    ) {

        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }

    }

}