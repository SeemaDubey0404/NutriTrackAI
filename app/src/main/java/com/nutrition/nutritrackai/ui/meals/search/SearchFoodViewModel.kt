package com.nutrition.nutritrackai.ui.meals.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrition.nutritrackai.data.remote.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFoodViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SearchFoodUiState()
    )

    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun onQueryChanged(
        query: String
    ) {

        _uiState.value = _uiState.value.copy(
            query = query
        )

        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            if (query.isBlank()) {

                _uiState.value = _uiState.value.copy(
                    foods = emptyList()
                )

                return@launch
            }

            delay(500)

            _uiState.value = _uiState.value.copy(
                isLoading = true
            )

            val foods = repository.searchFood(query)

            _uiState.value = _uiState.value.copy(
                foods = foods,
                isLoading = false
            )

        }

    }

}