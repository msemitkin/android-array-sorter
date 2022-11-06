package com.github.msemitkin.mobile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NumbersViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NumbersUiState())
    val uiState: StateFlow<NumbersUiState> = _uiState.asStateFlow()

    fun setNumbers(numbers: List<Int>) {
        _uiState.update { currentState ->
            currentState.copy(numbers = numbers)
        }
    }

    fun setInputString(string: String) {
        _uiState.update { currentState ->
            currentState.copy(inputString = string)
        }
    }

    fun setSortingResult(sortedNumbersState: String, sortingIterationsCountState: String) {
        _uiState.update { currentState ->
            currentState.copy(
                sortedNumbersState = sortedNumbersState,
                sortingIterationsCountState = sortingIterationsCountState
            )
        }
    }

    fun setInvalidInput(invalidInput: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(invalidInput = invalidInput)
        }
    }

    fun setChosenStrategyName(strategy: String) {
        _uiState.update { currentState ->
            currentState.copy(chosenStrategyName = strategy)
        }
    }

    fun reset() {
        _uiState.update { NumbersUiState() }
    }

}
