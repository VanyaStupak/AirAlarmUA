package com.example.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.widget.model.AlertsUiModel
import dev.stupak.common.formatDateTime
import dev.stupak.ui.R
import dev.stupak.ui.theme.LocalAppTheme

@Composable
fun AlertsItem(
    alert: AlertsUiModel?,
    modifier: GlanceModifier,
    showIcon: Boolean = true,
    showStatus: Boolean = false,
) {
    val colors = LocalAppTheme.current.colors
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val (icon, accentColor) = getAlertIconAndColor(alert?.alertType ?: "")
        if (showIcon) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    provider = ImageProvider(R.drawable.ic_circle),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(ColorProvider(accentColor)),
                )

                Image(
                    provider = ImageProvider(icon),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(ColorProvider(Color.Black)),
                )
            }

            Spacer(modifier = GlanceModifier.width(8.dp))
        }

        Column {
            Text(
                text = alert?.locationTitle ?: "",
                style =
                    TextStyle(
                        color = ColorProvider(if (showStatus) Color.White else accentColor),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    ),
            )
            if (showStatus) {
                Text(
                    text = "Повітряна тривога!",
                    style =
                        TextStyle(
                            color = ColorProvider(colors.secondary100),
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                        ),
                )
            }
            Text(
                text = formatDateTime(alert?.startedAt ?: ""),
                style =
                    TextStyle(
                        color = ColorProvider(Color.White),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    ),
            )
        }
    }
}

@Composable
fun getAlertIconAndColor(alertType: String): Pair<Int, Color> =
    when (alertType) {
        "air_raid" -> R.drawable.ic_air_raid to LocalAppTheme.current.colors.mapAlert
        "artillery_shelling" -> R.drawable.ic_artillery to LocalAppTheme.current.colors.warning
        else -> R.drawable.ic_ak47 to LocalAppTheme.current.colors.warning
    }
