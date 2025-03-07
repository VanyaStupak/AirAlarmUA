package com.example.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.width
import androidx.glance.unit.ColorProvider

@Composable
fun Divider(color: ColorProvider) {
    Spacer(
        modifier = GlanceModifier
            .fillMaxHeight()
            .width(1.dp)
            .background(color)
    )
}