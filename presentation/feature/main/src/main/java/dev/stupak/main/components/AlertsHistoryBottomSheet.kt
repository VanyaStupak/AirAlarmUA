package dev.stupak.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.stupak.common.Strings
import dev.stupak.main.MainScreenState
import dev.stupak.ui.R
import dev.stupak.ui.theme.Theme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertsHistoryBottomSheet(
    noInternet: Boolean,
    historyError: String?,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    regionName: String,
    uiState: MainScreenState,
    selectedRange: String,
    onRangeSelected: (String) -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier.statusBarsPadding(),
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
        scrimColor = Color.Transparent,
        shape = RoundedCornerShape(32.dp),
        containerColor = Theme.color.statsBackground,
        dragHandle = {
            Box(
                modifier =
                    Modifier
                        .padding(8.dp)
                        .width(50.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Theme.color.primary120),
            )
        },
    ) {
        Column {
            Text(
                modifier =
                    Modifier
                        .padding(start = 12.dp, end = 12.dp, top = 8.dp),
                text = regionName,
                style = Theme.typography.heading4,
                color = Theme.color.neutral9,
            )
            if (noInternet || historyError != null) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(400.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.history_error),
                        color = Theme.color.neutral9,
                    )
                }
            }
            val history = uiState.alertsHistoryList
            val dateFormat = SimpleDateFormat(Strings.DATE_PATTERN, Locale.getDefault())
            val outputFormat = SimpleDateFormat(Strings.DD_MM, Locale.getDefault())

            val filteredHistory = history.filter { it.locationTitle == regionName }

            val historyDates = filteredHistory.map { dateFormat.parse(it.startedAt) }.sortedDescending()
            val today = Calendar.getInstance().time
            val lastDate = historyDates.firstOrNull { !it.after(today) } ?: return@Column

            val calendar = Calendar.getInstance()
            calendar.time = lastDate
            calendar.add(Calendar.MONTH, -1)
            val monthAgo = calendar.time

            val allDateList = mutableListOf<String>()
            calendar.time = lastDate

            while (!calendar.time.before(monthAgo)) {
                allDateList.add(outputFormat.format(calendar.time))
                calendar.add(Calendar.DAY_OF_MONTH, -1)
            }

            val groupedData =
                filteredHistory
                    .groupingBy { outputFormat.format(dateFormat.parse(it.startedAt)) }
                    .eachCount()

            val finalGroupedData = allDateList.associateWith { groupedData[it] ?: 0.03 }

            val indicesList = finalGroupedData.keys.toList()
            val valuesList = finalGroupedData.values.map { it.toDouble() }

            val groupedDataDurations =
                filteredHistory
                    .groupBy { outputFormat.format(dateFormat.parse(it.startedAt)) }
                    .mapValues { (_, alerts) ->
                        alerts
                            .filter { it.finishedAt != null }
                            .sumOf {
                                val startedAt = dateFormat.parse(it.startedAt).time
                                val finishedAt = dateFormat.parse(it.finishedAt).time
                                val durationInMillis = finishedAt - startedAt
                                durationInMillis / (1000.0 * 60 * 60)
                            }
                    }

            val finalDurationsData = allDateList.associateWith { groupedDataDurations[it] ?: 0.03 }
            val durationValuesList = finalDurationsData.values.toList()

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HistoryInfo(
                    modifier = Modifier,
                )

                Spacer(modifier = Modifier.weight(1f))

                HistoryToggleButtons(
                    modifier = Modifier,
                    selectedRange = selectedRange,
                ) {
                    onRangeSelected(it)
                }
            }

            fun filterData(
                range: String,
                indices: List<String>,
                values: List<Double>,
            ): Pair<List<String>, List<Double>> {
                val filteredIndices =
                    when (range) {
                        "week" -> indices.take(7)
                        "month" -> indices
                        else -> indices
                    }

                val filteredValues =
                    when (range) {
                        "week" -> values.take(7)
                        "month" -> values
                        else -> values
                    }

                return (filteredIndices + "") to (filteredValues + 0.0)
            }

            val (filteredIndices, filteredValues) =
                filterData(
                    selectedRange,
                    indicesList,
                    valuesList,
                )
            val (_, filteredDurationValues) =
                filterData(
                    selectedRange,
                    indicesList,
                    durationValuesList,
                )
            val totalAlerts = filteredValues.sum().toInt()
            val totalDurationInHours = filteredDurationValues.sum()
            val totalDurationInMinutes = (totalDurationInHours * 60).toInt()
            val hours = totalDurationInMinutes / 60
            val minutes = totalDurationInMinutes % 60
            if (uiState.isHistoryLoading) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        color = Theme.color.secondary100,
                    )
                }
            } else {
                HistoryBarChart(
                    yAxisData1 = filteredValues,
                    xAxisData = filteredIndices,
                    yAxisData2 = filteredDurationValues,
                )
            }
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Theme.color.statsAmount, RoundedCornerShape(32.dp))
                            .padding(32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(R.string.total_alerts),
                        color = Theme.color.toggleText,
                        style = Theme.typography.heading6,
                    )

                    Text(
                        text = totalAlerts.toString(),
                        color = Theme.color.toggleText,
                        style = Theme.typography.heading6,
                    )
                }

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Theme.color.statsTime, RoundedCornerShape(32.dp))
                            .padding(32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(R.string.total_alerts_time),
                        color = Theme.color.toggleText,
                        style = Theme.typography.heading6,
                    )

                    Text(
                        text =
                            "$hours ${stringResource(id = R.string.hours_and_minutes)}" +
                                " $minutes ${stringResource(R.string.minutes)}",
                        color = Theme.color.toggleText,
                        style = Theme.typography.heading6,
                    )
                }
            }
        }
    }
}
