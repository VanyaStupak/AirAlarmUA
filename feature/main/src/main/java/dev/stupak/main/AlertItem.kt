package dev.stupak.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.sp
import dev.stupak.main.model.AlertsUiModel
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.stupak.main.util.Const
import dev.stupak.main.util.formatDateTime
import dev.stupak.main.util.getElapsedTime
import dev.stupak.ui.R
import dev.stupak.ui.theme.AirAlarmTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun AlertItem(alert: AlertsUiModel) {

    val (icon, accentColor) = getAlertIconAndColor(alert.alertType,)

    var elapsedTime by remember { mutableStateOf(getElapsedTime(alert.startedAt)) }

    LaunchedEffect(key1 = alert.startedAt, key2 = alert.id) {
        while (isActive) {
            delay(60 * 1000L)
            elapsedTime = getElapsedTime(alert.startedAt)
        }
    }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .background(AirAlarmTheme.colors.primary100, shape = RoundedCornerShape(32.dp))
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(accentColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Alert Icon",
                    tint = AirAlarmTheme.colors.neutral10,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = alert.locationTitle,
                    style = AirAlarmTheme.typography.heading6,
                    color = AirAlarmTheme.colors.neutral05
                )

                if(alert.locationType != "oblast") {
                    Text(
                        modifier = Modifier.padding(bottom = 2.dp),
                        text = alert.locationOblast,
                        color = AirAlarmTheme.colors.neutral5,
                        style = AirAlarmTheme.typography.textMediumNormal,
                    )
                }



                Text(
                    text =  when (alert.alertType) {
                        "air_raid" -> Const.AIR_ALARM
                        "artillery_shelling" -> Const.ARTILLERY_SHELLING
                        "urban_fights" -> Const.URBAN_FIGHTS
                        else -> Const.AIR_ALARM
                    },
                    color = accentColor,
                    style = AirAlarmTheme.typography.textLargeNormal,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatDateTime(alert.startedAt),
                        color = AirAlarmTheme.colors.neutral05,
                        style = AirAlarmTheme.typography.textRegularNormal
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "|",
                        color = AirAlarmTheme.colors.neutral4,
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = elapsedTime,
                        color = AirAlarmTheme.colors.secondary40,
                        style = AirAlarmTheme.typography.textRegularNormal,
                    )
                }
            }
        }
}

@Composable
fun getAlertIconAndColor(alertType: String): Pair<ImageVector, Color> {
    return when (alertType) {
        "air_raid" -> ImageVector.vectorResource(id = R.drawable.ic_air_raid) to AirAlarmTheme.colors.secondary100
        "artillery_shelling" -> ImageVector.vectorResource(id = R.drawable.ic_artillery) to AirAlarmTheme.colors.warning
        else -> ImageVector.vectorResource(id = R.drawable.ic_ak47) to AirAlarmTheme.colors.warning
    }
}