package dev.stupak.ui.theme

import androidx.compose.runtime.Composable
import dev.stupak.ui.ColorPalette
import dev.stupak.ui.provider.LocalThemeColor
import dev.stupak.ui.provider.LocalThemeTypography

object Theme {
    val color: ColorPalette
        @Composable
        get() = LocalThemeColor.current
    val typography: Typography
        @Composable
        get() = LocalThemeTypography.current
}
