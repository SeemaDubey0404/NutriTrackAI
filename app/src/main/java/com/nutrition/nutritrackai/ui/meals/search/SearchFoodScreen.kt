package com.nutrition.nutritrackai.ui.meals.search


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nutrition.nutritrackai.core.navigation.NavRoutes

@Composable
fun SearchFoodScreen(
    navController: NavHostController,
    viewModel: SearchFoodViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "←",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Text(
                    text = "Search Food",
                    style = MaterialTheme.typography.headlineMedium
                )

            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                value = state.query,
                onValueChange = viewModel::onQueryChanged,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                placeholder = {
                    Text("Search paneer, rice, milk...")
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(state.foods) { food ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                navController.navigate(
                                    NavRoutes.FoodDetails.route
                                )

                            },
                        shape = RoundedCornerShape(20.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AsyncImage(
                                model = food.imageUrl,
                                contentDescription = null,
                                modifier = Modifier.size(56.dp)
                            )

                            Spacer(
                                modifier = Modifier.size(16.dp)
                            )

                            Column {

                                Text(
                                    text = food.name ?: "Unknown food",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = "${food.calories} kcal / 100g",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                            }

                        }

                    }

                }

            }

        }

        if (state.isLoading) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

        }

    }
}