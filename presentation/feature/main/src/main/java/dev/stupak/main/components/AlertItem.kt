package dev.stupak.main.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.stupak.common.formatDateTime
import dev.stupak.common.getElapsedTime
import dev.stupak.main.model.AlertsUiModel
import dev.stupak.ui.R
import dev.stupak.ui.theme.Theme

@Composable
fun AlertItem(alert: AlertsUiModel) {
    val (icon, accentColor) = getAlertIconAndColor(alert.alertType)
    var elapsedTime by remember { mutableStateOf(getElapsedTime(alert.startedAt)) }
    val context = LocalContext.current
    // TODO fix
    DisposableEffect(Unit) {
        val receiver =
            object : BroadcastReceiver() {
                override fun onReceive(
                    context: Context?,
                    intent: Intent?,
                ) {
                    if (intent?.action == Intent.ACTION_TIME_TICK) {
                        elapsedTime = getElapsedTime(alert.startedAt)
                    }
                }
            }

        val filter = IntentFilter(Intent.ACTION_TIME_TICK)
        context.registerReceiver(receiver, filter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(Theme.color.neutral2)
                .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .size(40.dp)
                    .background(accentColor, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Alert Icon",
                tint = Theme.color.neutral9,
                modifier = Modifier.size(24.dp),
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier =
                Modifier
                    .weight(1f),
        ) {
            Text(
                text = alert.locationTitle,
                style = Theme.typography.heading6,
                color = Theme.color.neutral9,
            )

            if (alert.locationType != "oblast") {
                Text(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = alert.locationOblast,
                    color = Theme.color.neutral8,
                    style = Theme.typography.textMediumNormal,
                )
            }

            Text(
                text =
                    when (alert.alertType) {
                        "air_raid" -> stringResource(R.string.air_raid_alert)
                        "artillery_shelling" -> stringResource(R.string.threat_of_shelling)
                        "urban_fights" -> stringResource(R.string.street_fights)
                        else -> stringResource(R.string.air_raid_alert)
                    },
                color =
                    if (alert.alertType != "air_raid") {
                        Theme.color.warning2
                    } else {
                        accentColor
                    },
                style = Theme.typography.textMediumNormal,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = formatDateTime(alert.startedAt),
                    color = Theme.color.neutral8,
                    style = Theme.typography.textRegularNormal,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "|",
                    color = Theme.color.neutral6,
                    fontSize = 12.sp,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = elapsedTime,
                    color = Theme.color.neutral7,
                    style = Theme.typography.textRegularNormal,
                )
            }
        }
    }
}

@Composable
fun getAlertIconAndColor(alertType: String): Pair<ImageVector, Color> =
    when (alertType) {
        "air_raid" -> ImageVector.vectorResource(id = R.drawable.ic_air_raid) to Theme.color.mapAlert
        "artillery_shelling" -> ImageVector.vectorResource(id = R.drawable.ic_artillery) to Theme.color.warning
        else -> ImageVector.vectorResource(id = R.drawable.ic_ak47) to Theme.color.warning
    }
