package dev.stupak.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.stupak.ui.theme.LocalAppTheme

@Composable
fun ToggleButtons(
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    val colors = LocalAppTheme.current.colors
    val typography = LocalAppTheme.current.typography

    Row(
        modifier =
            Modifier
                .clip(RoundedCornerShape(360.dp))
                .background(
                    if (isSelected) {
                        colors.toggleButton
                    } else {
                        Color.Transparent
                    },
                ).padding(vertical = 4.dp, horizontal = 12.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    onClick()
                },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1f, fill = false),
            color = if (isSelected) colors.toggleText else colors.neutral9,
            style = typography.textMediumMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
