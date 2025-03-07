package com.example.widget.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.unit.ColorProvider
import com.example.widget.model.AlertsUiModel
import dev.stupak.ui.theme.LocalAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DefaultAlertsView(
    regionName: String?,
    filteredList: List<AlertsUiModel>
) {
    val colors = LocalAppTheme.current.colors
    Row(
        modifier = GlanceModifier
            .fillMaxWidth()
            .height(170.dp)
            .background(ColorProvider(colors.neutral9)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AlertsItem(
            alert = filteredList.find { it.locationTitle == regionName },
            modifier = GlanceModifier.padding(start = 8.dp).defaultWeight(),
            showStatus = true,
            showIcon = false
        )

        Divider(ColorProvider(colors.neutral7))


        Box(
            modifier = GlanceModifier
                .fillMaxHeight()
                .padding(start = 8.dp)
                .defaultWeight(),
            contentAlignment = Alignment.Center
        ) {
            AlertListWidget(filteredList.filter { alert -> alert.locationTitle != regionName })
        }
    }
}
