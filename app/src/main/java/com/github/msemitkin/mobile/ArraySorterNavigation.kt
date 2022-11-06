package com.github.msemitkin.mobile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ArraySorterNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: NumbersViewModel = NumbersViewModel()
) {
    val uiState: NumbersUiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            ArraySorterForm(
                onChartButtonClicked = {
                    viewModel.setNumbers(it)
                    navController.navigate("chart")
                }
            )
        }
        composable("chart") {
            ChartComposable(uiState.numbers)
        }
    }
}
