package com.github.msemitkin.mobile

import android.content.Context
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.msemitkin.mobile.sorting.*
import java.util.*

@Composable
fun ArraySorterNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: NumbersViewModel = NumbersViewModel(),
    context: Context
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
    Scaffold(topBar = {
        ApplicationHeader(onAuthorClick = { navController.navigate("author") })
    }) {
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
                    onSave = {
                        val fileName = saveContent(uiState.inputString.toByteArray(), context)
                        Toast.makeText(context, "Saved to $fileName", Toast.LENGTH_SHORT).show()
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
            composable("author") {
                Author()
            }
        }
    }
}

@Composable
fun ApplicationHeader(onAuthorClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More"
                )
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(onClick = onAuthorClick) {
                    Text(text = "Author")
                }
            }
        }
    )
}

private fun saveContent(content: ByteArray, context: Context): String {
    val currentTime: Date = Calendar.getInstance().time
    val fileName = cleanUpString(currentTime.toString())
    context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        it.write(content)
    }
    return fileName
}

private fun cleanUpString(string: String): String {
    return String(
        string.toCharArray()
            .map {
                if (it in 'a'..'b' || it in 'A'..'B' || it in '0'..'9') it
                else '_'
            }.toCharArray()
    ).replace("_", "")
}

