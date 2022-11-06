package com.github.msemitkin.mobile

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NumbersViewModel {
    private val _uiState = MutableStateFlow(NumbersUiState(emptyList()))
    val uiState: StateFlow<NumbersUiState> = _uiState.asStateFlow()

    fun setNumbers(numbers: List<Int>) {
        _uiState.update { currentState ->
            currentState.copy(numbers = numbers)
        }
    }

}
