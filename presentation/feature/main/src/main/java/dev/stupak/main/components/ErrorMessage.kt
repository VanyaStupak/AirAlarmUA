package dev.stupak.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.stupak.ui.theme.Theme

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Theme.color.networkConnectivity)
                .padding(vertical = 4.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = message,
            style = Theme.typography.textRegularNormal,
            color = Theme.color.white,
        )
    }
}
