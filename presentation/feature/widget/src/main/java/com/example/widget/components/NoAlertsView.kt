package com.example.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentHeight
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.stupak.ui.theme.LocalAppTheme

@Composable
fun NoAlertsView(
    regionName: String?
) {
    val colors = LocalAppTheme.current.colors

    Column(
        modifier = GlanceModifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .background(ColorProvider(colors.neutral9))
    ) {
        Text(
            text = regionName ?: "",
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        )
        Spacer(modifier = GlanceModifier.height(4.dp))
        Text(
            text = "Наразі тривог немає",
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        )
    }
}
