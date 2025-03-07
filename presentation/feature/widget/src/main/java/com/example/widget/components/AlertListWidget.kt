package com.example.widget.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.layout.fillMaxWidth
import com.example.widget.model.AlertsUiModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlertListWidget(alerts: List<AlertsUiModel>) {

    LazyColumn(modifier = GlanceModifier.fillMaxWidth()) {
        items(alerts.size) { index ->
            val alert = alerts[index]
            AlertsItem(alert = alert, modifier = GlanceModifier.fillMaxWidth())
        }
    }
}
