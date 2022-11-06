package com.github.msemitkin.mobile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.msemitkin.mobile.sorting.*
import com.github.msemitkin.mobile.ui.theme.AndroidArraySorterTheme

@Composable
fun ArraySorterForm(
    onChartButtonClicked: (List<Int>) -> Unit
) {
    val defaultSortingStrategy = stringResource(R.string.default_sorting_strategy)
    val numberSeparator = stringResource(R.string.number_separator)
    val strategies: Map<String, SortingStrategy> = mapOf(
        "Merge Sort" to MergeSort(),
        "Bubble Sort" to BubbleSort(),
        "Insertion Sort" to InsertionSort(),
        "Quick Sort" to QuickSort(),
        "Selection Sort" to SelectionSort()
    )

    var isError by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf("") }
    var sortedTextState by remember { mutableStateOf("") }
    var numberOfIterationsState by remember { mutableStateOf("") }
    var chosenSortingStrategyName by remember { mutableStateOf(defaultSortingStrategy) }
    var inputNumbers by remember { mutableStateOf(listOf<Int>()) }

    val onSort = {
        try {
            inputNumbers = parseNumbers(textState, numberSeparator)
            isError = false
            val sortingResult: SortingResult<Int> =
                strategies[chosenSortingStrategyName]!!.sort(inputNumbers)
            sortedTextState = sortingResult.sortedValues.joinToString(numberSeparator)
            numberOfIterationsState = sortingResult.numberOfIterations.toString()
        } catch (e: Exception) {
            isError = true
        }
    }
    val onClear = {
        inputNumbers = emptyList()
        textState = ""
        sortedTextState = ""
        numberOfIterationsState = ""
        isError = false
    }

    val onChart = {
        inputNumbers
            .ifEmpty { parseNumbers(textState, numberSeparator) }
            .run(onChartButtonClicked)
    }

    Box(
        modifier = Modifier
            .wrapContentHeight(align = Alignment.Top)
            .wrapContentWidth(align = Alignment.CenterHorizontally)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            OutlinedTextField(
                value = textState,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { textState = it },
                shape = RoundedCornerShape(15),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    errorBorderColor = MaterialTheme.colors.error,
                    unfocusedBorderColor = MaterialTheme.colors.primary
                ),
                isError = isError
            )
            Column(horizontalAlignment = Alignment.Start) {
                strategies.forEach { (strategyName, _) ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = strategyName == chosenSortingStrategyName,
                            onClick = { chosenSortingStrategyName = strategyName }
                        )
                        Text(
                            text = strategyName,
                            modifier = Modifier.padding(start = 8.dp),
                            fontSize = 10.sp
                        )
                    }
                }
            }
            Row {
                Button(
                    onClick = onSort,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(85.dp)
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                ) {
                    Text(text = "Sort")
                }
                Button(
                    onClick = onClear,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(85.dp)
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                ) {
                    Text(text = "Clear")
                }
                Button(
                    onClick = onChart,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(85.dp)
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                ) {
                    Text(text = "Chart")
                }
            }
            OutlinedTextField(
                value = sortedTextState,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { sortedTextState = it },
                shape = RoundedCornerShape(15),
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = MaterialTheme.colors.primary,
                    focusedBorderColor = MaterialTheme.colors.primary
                )
            )
            OutlinedTextField(
                value = numberOfIterationsState,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { numberOfIterationsState = it },
                shape = RoundedCornerShape(15),
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = MaterialTheme.colors.primary,
                    focusedBorderColor = MaterialTheme.colors.primary
                ),
                label = { Text(text = "Number of iterations") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArraySorterFormPreview() {
    AndroidArraySorterTheme {
        ArraySorterNavigation()
    }
}