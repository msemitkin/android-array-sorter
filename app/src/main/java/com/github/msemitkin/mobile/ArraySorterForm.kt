package com.github.msemitkin.mobile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.msemitkin.mobile.sorting.SortingStrategy

@Composable
fun ArraySorterForm(
    onSort: () -> Unit,
    onClear: () -> Unit,
    onChart: () -> Unit,
    onSave: () -> Unit,
    onInputUpdate: (String) -> Unit,
    onStrategyChange: (String) -> Unit,
    strategies: Map<String, SortingStrategy>,
    uiState: NumbersUiState
) {
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
                value = uiState.inputString,
                onValueChange = { onInputUpdate(it) },
                shape = RoundedCornerShape(15),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    errorBorderColor = MaterialTheme.colors.error,
                    unfocusedBorderColor = MaterialTheme.colors.primary
                ),
                isError = uiState.invalidInput
            )
            Column(horizontalAlignment = Alignment.Start) {
                strategies.forEach { (strategyName, _) ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = strategyName == uiState.chosenStrategyName,
                            onClick = { onStrategyChange(strategyName) }
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
                MenuButton(onClick = onSort, text = "Sort")
                MenuButton(onClick = onClear, text = "Clear")
            }
            Row {
                MenuButton(onClick = onChart, text = "Chart")
                MenuButton(onClick = onSave, text = "Save")
            }
            ReadOnlyField(
                value = uiState.sortedNumbersState
            )
            ReadOnlyField(
                value = uiState.sortingIterationsCountState,
                label = "Number of iterations"
            )
        }
    }
}

@Composable
private fun MenuButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .width(85.dp)
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    ) {
        Text(text = text)
    }
}

@Composable
private fun ReadOnlyField(value: String, label: String? = null) {
    OutlinedTextField(
        value = value,
        onValueChange = { },
        shape = RoundedCornerShape(15),
        readOnly = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colors.primary,
            focusedBorderColor = MaterialTheme.colors.primary
        ),
        label = { Text(text = label ?: "") }
    )
}
