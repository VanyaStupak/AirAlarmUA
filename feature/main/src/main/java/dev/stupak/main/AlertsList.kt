package dev.stupak.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.stupak.main.model.AlertsUiModel
import dev.stupak.main.model.UiAlertsList
import dev.stupak.main.util.parseDate
import dev.stupak.ui.theme.AirAlarmTheme

@Composable
fun AlertsList(
    alerts: List<AlertsUiModel>,
    modifier: Modifier = Modifier,
    listState: LazyListState
) {

    val sortedAlerts = alerts.sortedByDescending { parseDate(it.startedAt) }

    LazyColumn(
        modifier = modifier
            .background(AirAlarmTheme.colors.neutral05)
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
       items(sortedAlerts){ alert ->
            AlertItem(alert = alert)
        }
    }

}