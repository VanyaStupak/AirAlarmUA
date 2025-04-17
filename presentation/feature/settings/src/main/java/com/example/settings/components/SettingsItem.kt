package com.example.settings.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.stupak.ui.theme.Theme

@Composable
fun SettingsItem(
    imageVector: ImageVector,
    title: String,
    contentColor: Color = Theme.color.white,
    modifier: Modifier = Modifier,
    endContent: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "ItemImage",
            tint = contentColor,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            style = Theme.typography.textLargeMedium,
            color = contentColor,
            modifier = Modifier.weight(1f),
        )

        Spacer(modifier = Modifier.width(8.dp))

        endContent()
    }
}
