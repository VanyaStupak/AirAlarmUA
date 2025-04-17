package dev.stupak.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters
import com.aay.compose.baseComponents.model.LegendPosition
import dev.stupak.ui.theme.Theme

@Composable
fun HistoryBarChart(
    yAxisData1: List<Double>,
    yAxisData2: List<Double>,
    xAxisData: List<String>,
) {
    val testBarParameters: List<BarParameters> =
        listOf(
            BarParameters(
                dataName = "",
                data = yAxisData1,
                barColor = Theme.color.statsBar,
            ),
            BarParameters(
                dataName = "",
                data = yAxisData2,
                barColor = Theme.color.warning,
            ),
        )

    Box(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .height(330.dp),
    ) {
        BarChart(
            chartParameters = testBarParameters,
            gridColor = Theme.color.neutral8,
            xAxisData = xAxisData,
            isShowGrid = false,
            animateChart = true,
            horizontalArrangement = Arrangement.Center,
            spaceBetweenBars = 3.dp,
            spaceBetweenGroups = 24.dp,
            showGridWithSpacer = true,
            yAxisStyle =
                TextStyle(
                    fontSize = 14.sp,
                    color = Theme.color.neutral9,
                ),
            xAxisStyle =
                TextStyle(
                    fontSize = 14.sp,
                    color = Theme.color.neutral9,
                    fontWeight = FontWeight.W400,
                ),
            yAxisRange = 8,
            barWidth = 10.dp,
            legendPosition = LegendPosition.DISAPPEAR,
        )
    }
}
