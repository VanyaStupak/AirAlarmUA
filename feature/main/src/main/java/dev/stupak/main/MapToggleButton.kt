package dev.stupak.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.stupak.ui.R
import dev.stupak.ui.theme.AirAlarmTheme

@Composable
fun MapToggleButton(
    modifier: Modifier,
    selectedMap: String,
    onMapChange: (String) -> Unit
) {
    val maps = listOf("Ukraine", "Oblast")

    Row(
        modifier = modifier
            .background(AirAlarmTheme.colors.neutral2, RoundedCornerShape(32.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        maps.forEachIndexed { index, map ->
            val isSelected = map == selectedMap


            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) AirAlarmTheme.colors.primary120 else Color.Transparent,
                animationSpec = tween(durationMillis = 100), label = ""
            )

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(360.dp))
                    .background(backgroundColor)
                    .clickable {
                        onMapChange(map)
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (isSelected) {
                        Text(
                            text = if (map == "Ukraine") "Україна" else "Область",
                            color = Color.White,
                            style = AirAlarmTheme.typography.textMediumMedium
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (map == "Oblast") R.drawable.ic_pin_area else R.drawable.ic_ukraine
                        ),
                        contentDescription = "Map",
                        tint = if (isSelected) AirAlarmTheme.colors.neutral05 else AirAlarmTheme.colors.neutral9
                    )
                }
            }
        }
    }
}
