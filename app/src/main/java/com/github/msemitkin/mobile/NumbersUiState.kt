package com.github.msemitkin.mobile

data class NumbersUiState(
    val inputString: String = "",
    val invalidInput: Boolean = false,
    val numbers: List<Int> = emptyList(),
    val sortedNumbersState: String = "",
    val sortingIterationsCountState: String = "",
    val chosenStrategyName: String = "Merge Sort",
)
