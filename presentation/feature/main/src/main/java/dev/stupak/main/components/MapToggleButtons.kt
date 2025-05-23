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
import dev.stupak.ui.theme.Theme

@Composable
fun MapToggleButtons(
    modifier: Modifier = Modifier,
    selectedMap: String,
    onMapChange: (String) -> Unit,
    oblast: String,
) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(32.dp))
                .background(Theme.color.toggle)
                .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ToggleButtons(
            isSelected = selectedMap == "Ukraine",
            text = stringResource(dev.stupak.localisation.R.string.ukraine),
            onClick = { onMapChange("Ukraine") },
        )

        ToggleButtons(
            isSelected = selectedMap == "Oblast",
            text = oblast,
            onClick = { onMapChange("Oblast") },
        )
    }
}
