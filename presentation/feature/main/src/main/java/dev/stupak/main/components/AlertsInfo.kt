package dev.stupak.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.stupak.ui.theme.Theme

@Composable
fun AlertsInfo(
    modifier: Modifier,
    isRegionShown: Boolean,
) {
    Column(
        modifier = modifier,
    ) {
        Row {
            Icon(
                imageVector = ImageVector.vectorResource(dev.stupak.ui.R.drawable.ic_red_circle),
                contentDescription = null,
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text =
                    if (isRegionShown) {
                        stringResource(dev.stupak.localisation.R.string.air_raid_alert_in_region)
                    } else {
                        stringResource(dev.stupak.localisation.R.string.air_raid_alert_in_area)
                    },
                style = Theme.typography.textRegularNormal,
                color = Theme.color.neutral9,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Icon(
                imageVector = ImageVector.vectorResource(dev.stupak.ui.R.drawable.ic_yellow_circle),
                contentDescription = null,
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text =
                    if (isRegionShown) {
                        stringResource(dev.stupak.localisation.R.string.other_types_of_alerts)
                    } else {
                        stringResource(dev.stupak.localisation.R.string.partial_alert_in_districts)
                    },
                style = Theme.typography.textRegularNormal,
                color = Theme.color.neutral9,
            )
        }
    }
}
