package com.github.msemitkin.mobile

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.bytebeats.views.charts.bar.BarChart
import me.bytebeats.views.charts.bar.BarChartData
import me.bytebeats.views.charts.bar.render.bar.SimpleBarDrawer
import me.bytebeats.views.charts.bar.render.label.SimpleLabelDrawer
import me.bytebeats.views.charts.bar.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.bar.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
fun ChartComposable(uiState: NumbersUiState) {
    val histogramData = uiState.numbers
        .groupBy { it }
        .mapValues { (_, numbers) -> numbers.size }
    val bars = histogramData
        .map { (number, count) -> BarChartData.Bar(count.toFloat(), Color.LightGray, "$number") }
        .ifEmpty { (1..5).map { BarChartData.Bar(0.0f, Color.LightGray, "$it") } }
    Box(
        modifier = Modifier
            .height(400.dp)
            .padding(10.dp)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    ) {
        BarChart(
            barChartData = BarChartData(
                bars = bars
            ),
            modifier = Modifier.fillMaxSize(),
            animation = simpleChartAnimation(),
            barDrawer = SimpleBarDrawer(),
            xAxisDrawer = SimpleXAxisDrawer(),
            yAxisDrawer = SimpleYAxisDrawer(
                labelValueFormatter = { it.toInt().toString() }
            ),
            labelDrawer = SimpleLabelDrawer()
        )
    }
}