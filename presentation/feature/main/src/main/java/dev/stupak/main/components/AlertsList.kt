package dev.stupak.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.stupak.common.parseDate
import dev.stupak.main.model.AlertsUiModel
import dev.stupak.ui.theme.LocalAppTheme

@Composable
fun AlertsList(
    alerts: List<AlertsUiModel>,
    modifier: Modifier = Modifier,
    listState: LazyListState,
) {
    val colors = LocalAppTheme.current.colors
    val typography = LocalAppTheme.current.typography

    val sortedAlerts = alerts.sortedByDescending { parseDate(it.startedAt) }

    if (sortedAlerts.isEmpty()) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(dev.stupak.ui.R.string.no_alerts_now),
                style = typography.textMediumNormal,
                color = colors.neutral9,
            )
        }
    } else {
        LazyColumn(
            modifier =
                modifier
                    .fillMaxSize(),
            contentPadding =
                PaddingValues(
                    bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = listState,
        ) {
            itemsIndexed(
                items = sortedAlerts,
                key = { _, alert -> alert.id },
            ) { _, alert ->
                AlertItem(alert = alert)
            }
        }
    }
}
