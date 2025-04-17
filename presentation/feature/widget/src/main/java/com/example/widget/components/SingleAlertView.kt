package com.example.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.unit.ColorProvider
import com.example.widget.model.AlertsUiModel
import dev.stupak.ui.theme.Theme

@Composable
fun SingleAlertView(
    regionName: String?,
    alertsList: List<AlertsUiModel>,
) {
    Row(
        modifier =
            GlanceModifier
                .fillMaxWidth()
                .background(ColorProvider(Theme.color.neutral9))
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AlertsItem(
            alert = alertsList.find { it.locationTitle == regionName },
            modifier = GlanceModifier.defaultWeight(),
            showStatus = true,
            showIcon = false,
        )
        Box(
            modifier = GlanceModifier,
            contentAlignment = Alignment.Center,
        ) {
            Image(
                provider = ImageProvider(dev.stupak.ui.R.drawable.ic_circle),
                contentDescription = null,
                modifier = GlanceModifier.height(36.dp).width(36.dp),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(ColorProvider(Theme.color.secondary100)),
            )

            Image(
                provider = ImageProvider(dev.stupak.ui.R.drawable.ic_air_raid),
                contentDescription = null,
                modifier = GlanceModifier.height(24.dp).width(24.dp),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(ColorProvider(Color.Black)),
            )
        }
    }
}
