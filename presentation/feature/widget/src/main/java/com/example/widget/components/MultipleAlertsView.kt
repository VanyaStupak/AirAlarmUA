package com.example.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentHeight
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.widget.model.AlertsUiModel
import dev.stupak.ui.theme.Theme

@Composable
fun MultipleAlertsView(
    regionName: String?,
    filteredList: List<AlertsUiModel>,
) {
    Row(
        modifier =
            GlanceModifier
                .fillMaxWidth()
                .height(170.dp)
                .background(ColorProvider(Theme.color.neutral9)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier =
                GlanceModifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .defaultWeight()
                    .padding(16.dp)
                    .background(ColorProvider(Theme.color.neutral9)),
        ) {
            Text(
                text = regionName ?: "",
                style =
                    TextStyle(
                        color = ColorProvider(Color.White),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    ),
            )
            Spacer(modifier = GlanceModifier.height(4.dp))
            Text(
                text = "Часткова тривога в районах",
                style =
                    TextStyle(
                        color = ColorProvider(Theme.color.warning),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                    ),
            )
        }

        Divider(ColorProvider(Theme.color.neutral7))

        Box(
            modifier =
                GlanceModifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .defaultWeight(),
            contentAlignment = Alignment.Center,
        ) {
            AlertListWidget(filteredList.filter { alert -> alert.locationTitle != regionName })
        }
    }
}
