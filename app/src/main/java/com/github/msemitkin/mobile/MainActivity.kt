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
import com.github.msemitkin.mobile.sorting.MergeSort
import com.github.msemitkin.mobile.ui.theme.AndroidArraySorterTheme

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
            Button(
                onClick = {
                    val numbers = textState.split(" ").map { it.toInt() }
                    val sortedNumbers = MergeSort().sort(numbers)
                    sortedTextState = sortedNumbers.joinToString(" ")
                },
                modifier = Modifier
                    .padding(16.dp)
                    .width(65.dp)
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            ) {
                Text(text = "Sort")
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


@Preview(showBackground = true)
@Composable
fun ComposePreview() {
    AndroidArraySorterTheme {
        Compose()
    }
}