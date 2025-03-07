package com.example.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.stupak.ui.theme.LocalAppTheme

@Composable
fun TopBar(onBackClick: () -> Unit) {

    val colors = LocalAppTheme.current.colors
    val typography = LocalAppTheme.current.typography

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(360.dp))
                .background(colors.neutral4)
                .clickable {
                    onBackClick()
                }
                .padding(vertical = 8.dp, horizontal = 16.dp)

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(dev.stupak.ui.R.drawable.ic_arrow_back),
                contentDescription = "Icon",
                tint = colors.neutral9
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = "Settings",
            style = typography.heading5,
            color = colors.neutral9,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(56.dp))

    }
}