package com.github.msemitkin.mobile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.msemitkin.mobile.sorting.*

@Composable
fun ArraySorterNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: NumbersViewModel = NumbersViewModel(),
) {
    val numberSeparator = stringResource(R.string.number_separator)
    val uiState: NumbersUiState by viewModel.uiState.collectAsState()

    val strategies: Map<String, SortingStrategy> = mapOf(
        "Merge Sort" to MergeSort(),
        "Bubble Sort" to BubbleSort(),
        "Insertion Sort" to InsertionSort(),
        "Quick Sort" to QuickSort(),
        "Selection Sort" to SelectionSort()
    )

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            ArraySorterForm(
                onSort = {
                    try {
                        val parsedNumbers = parseNumbers(uiState.inputString, numberSeparator)
                        val sortingResult =
                            strategies[uiState.chosenStrategyName]!!.sort(parsedNumbers)
                        viewModel.setNumbers(parsedNumbers)
                        viewModel.setInvalidInput(false)
                        viewModel.setSortingResult(
                            sortingResult.sortedValues.joinToString(numberSeparator),
                            sortingResult.numberOfIterations.toString()
                        )
                    } catch (e: Exception) {
                        viewModel.setInvalidInput(true)
                    }
                },
                onChart = {
                    try {
                        val numbers = parseNumbers(uiState.inputString, numberSeparator)
                        viewModel.setNumbers(numbers)
                        viewModel.setInvalidInput(false)
                        navController.navigate("chart")
                    } catch (e: Exception) {
                        viewModel.setInvalidInput(true)
                    }
                },
                onInputUpdate = { viewModel.setInputString(it) },
                onClear = { viewModel.reset() },
                onStrategyChange = { strategy -> viewModel.setChosenStrategyName(strategy) },
                strategies = strategies,
                uiState = uiState
            )
        }
        composable("chart") {
            ChartComposable(uiState)
        }
    }
}
