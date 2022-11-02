package com.github.msemitkin.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.msemitkin.mobile.sorting.*
import com.github.msemitkin.mobile.ui.theme.AndroidArraySorterTheme

private const val SEPARATOR = " "

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidArraySorterTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.background)
                ) {
                    Compose()
                }
            }
        }
    }
}

@Composable
fun Compose() {
    var textState by remember { mutableStateOf("") }
    var sortedTextState by remember { mutableStateOf("") }
    var chosenSortingStrategyName by remember { mutableStateOf("Merge Sort") }
    val strategies = mapOf(
        "Merge Sort" to MergeSort(),
        "Bubble Sort" to BubbleSort(),
        "Insertion Sort" to InsertionSort(),
        "Quick Sort" to QuickSort(),
        "Selection Sort" to SelectionSort()
    )

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
                shape = RoundedCornerShape(15)
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
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = {
                        val numbers = parseNumbers(textState)
                        val sortedNumbers = strategies[chosenSortingStrategyName]!!.sort(numbers)
                        sortedTextState = sortedNumbers.joinToString(SEPARATOR)
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .width(65.dp)
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                ) {
                    Text(text = "Sort")
                }
                Button(
                    onClick = {
                        textState = ""
                        sortedTextState = ""
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .width(85.dp)
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                ) {
                    Text(text = "Clear")
                }
            }
            OutlinedTextField(
                value = sortedTextState,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { sortedTextState = it },
                shape = RoundedCornerShape(15)
            )
        }
    }
}

private fun parseNumbers(textState: String): List<Number> =
    textState.trim()
        .split(SEPARATOR)
        .filter(String::isNotBlank)
        .map { it.toInt() }


@Preview(showBackground = true)
@Composable
fun ComposePreview() {
    AndroidArraySorterTheme {
        Compose()
    }
}