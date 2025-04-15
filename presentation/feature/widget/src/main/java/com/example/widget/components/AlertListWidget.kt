package com.example.widget.components

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.layout.fillMaxWidth
import com.example.widget.model.AlertsUiModel

@Composable
fun AlertListWidget(alerts: List<AlertsUiModel>) {
    LazyColumn(modifier = GlanceModifier.fillMaxWidth()) {
        items(alerts.size) { index ->
            val alert = alerts[index]
            AlertsItem(alert = alert, modifier = GlanceModifier.fillMaxWidth())
        }
    }
}
