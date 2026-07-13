package com.nutrition.nutritrackai.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrition.nutritrackai.data.local.entity.MealEntity
import com.nutrition.nutritrackai.domain.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val _meals = MutableStateFlow<List<MealEntity>>(
        emptyList()
    )

    val meals = _meals.asStateFlow()

    init {

        viewModelScope.launch {

            repository.getMeals().collect {

                _meals.value = it

            }

        }

    }

}