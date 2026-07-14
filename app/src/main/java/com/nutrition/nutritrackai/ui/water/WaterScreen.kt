package com.nutrition.nutritrackai.ui.water


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nutrition.nutritrackai.ui.home.HomeViewModel

@Composable
fun WaterScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val water by viewModel.water.collectAsStateWithLifecycle()

    val progress = (water / 3000f)
        .coerceIn(0f, 1f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Icon(
            imageVector = Icons.Default.WaterDrop,
            contentDescription = null,
            modifier = Modifier.size(72.dp)
        )

        Text(
            text = "Water Tracker",
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "%.1f / 3.0 L".format(
                        water / 1000f
                    ),
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth()
                )

            }

        }

        Column(

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            Text(

                text = "Quick Add",

                style = MaterialTheme.typography.titleMedium

            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                WaterButton(

                    text = "+100 ml",

                    onClick = {

                        viewModel.addWater(100)

                    }

                )

                WaterButton(

                    text = "+250 ml",

                    onClick = {

                        viewModel.addWater(250)

                    }

                )

                WaterButton(

                    text = "+500 ml",

                    onClick = {

                        viewModel.addWater(500)

                    }

                )

            }

        }

    }

}

@Composable
fun WaterButton(

    text: String,

    onClick: () -> Unit

) {

    Button(

        onClick = onClick

    ) {

        Text(text)

    }

}