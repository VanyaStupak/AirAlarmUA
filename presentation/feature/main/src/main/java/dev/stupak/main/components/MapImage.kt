package dev.stupak.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale

@Composable
fun MapImage(imageVector: ImageVector) {
    Image(
        imageVector = imageVector,
        contentDescription = "Map",
        modifier =
            Modifier
                .fillMaxWidth()
                .aspectRatio(1.3f),
        contentScale = ContentScale.Fit,
    )
}
