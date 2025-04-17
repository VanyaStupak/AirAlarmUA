package dev.stupak.ui.provider

import androidx.compose.runtime.staticCompositionLocalOf
import dev.stupak.ui.ColorPalette
import dev.stupak.ui.theme.Typography

internal val LocalThemeColor =
    staticCompositionLocalOf<ColorPalette> {
        error("No ColorPalette provided")
    }

internal val LocalThemeTypography =
    staticCompositionLocalOf<Typography> {
        error("No Typography provided")
    }
