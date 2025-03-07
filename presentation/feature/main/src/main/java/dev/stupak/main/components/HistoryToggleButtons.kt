package dev.stupak.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.stupak.ui.theme.LocalAppTheme

@Composable
fun HistoryToggleButtons(
    modifier: Modifier = Modifier,
    selectedRange: String,
    onRangeChange: (String) -> Unit,
) {
    val colors = LocalAppTheme.current.colors

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .background(colors.neutral2)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ToggleButtons(
            isSelected = selectedRange == "month",
            text = stringResource(dev.stupak.ui.R.string.month),
            onClick = { onRangeChange("month") }
        )

        ToggleButtons(
            isSelected = selectedRange == "week",
            text = stringResource(dev.stupak.ui.R.string.week),
            onClick = { onRangeChange("week") }
        )

    }
}