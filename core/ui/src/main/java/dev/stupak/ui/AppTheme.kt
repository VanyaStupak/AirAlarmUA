package dev.stupak.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import dev.stupak.ui.provider.LocalThemeColor
import dev.stupak.ui.provider.LocalThemeTypography
import dev.stupak.ui.theme.InfoFocus
import dev.stupak.ui.theme.LocalTypography
import dev.stupak.ui.theme.Neutral0
import dev.stupak.ui.theme.Neutral05
import dev.stupak.ui.theme.Neutral1
import dev.stupak.ui.theme.Neutral10
import dev.stupak.ui.theme.Neutral15
import dev.stupak.ui.theme.Neutral2
import dev.stupak.ui.theme.Neutral3
import dev.stupak.ui.theme.Neutral4
import dev.stupak.ui.theme.Neutral5
import dev.stupak.ui.theme.Neutral55
import dev.stupak.ui.theme.Neutral6
import dev.stupak.ui.theme.Neutral7
import dev.stupak.ui.theme.Neutral8
import dev.stupak.ui.theme.Neutral85
import dev.stupak.ui.theme.Neutral9
import dev.stupak.ui.theme.Neutral95
import dev.stupak.ui.theme.Primary100
import dev.stupak.ui.theme.Primary120
import dev.stupak.ui.theme.Primary80
import dev.stupak.ui.theme.Secondary10
import dev.stupak.ui.theme.Secondary100
import dev.stupak.ui.theme.Secondary120
import dev.stupak.ui.theme.Secondary40
import dev.stupak.ui.theme.Warning
import dev.stupak.ui.theme.Warning2

@Immutable
data class ColorPalette(
    val primary80: Color,
    val primary100: Color,
    val primary120: Color,
    val secondary10: Color,
    val secondary40: Color,
    val secondary100: Color,
    val secondary120: Color,
    val neutral0: Color,
    val neutral05: Color,
    val neutral1: Color,
    val neutral15: Color,
    val neutral2: Color,
    val neutral3: Color,
    val neutral4: Color,
    val neutral5: Color,
    val mapDefault: Color,
    val neutral6: Color,
    val neutral7: Color,
    val neutral8: Color,
    val neutral85: Color,
    val neutral9: Color,
    val neutral95: Color,
    val neutral10: Color,
    val warning: Color,
    val warning2: Color,
    val infoFocus: Color,
    val mapAlert: Color,
    val mapStroke: Color,
    val button: Color,
    val toggle: Color,
    val toggleText: Color,
    val toggleButton: Color,
    val mapText: Color,
    val switchTrack: Color,
    val white: Color,
    val statsBackground: Color,
    val statsBar: Color,
    val statsAmount: Color,
    val statsTime: Color,
    val networkConnectivity: Color,
)

val LightColorPalette =
    ColorPalette(
        primary80 = Primary80,
        primary100 = Primary100,
        primary120 = Primary120,
        secondary10 = Secondary10,
        secondary40 = Secondary40,
        secondary100 = Secondary100,
        secondary120 = Secondary120,
        neutral0 = Neutral0,
        neutral05 = Neutral05,
        neutral1 = Neutral1,
        neutral15 = Neutral15,
        neutral2 = Neutral2,
        neutral3 = Neutral3,
        neutral4 = Neutral4,
        neutral5 = Neutral5,
        mapDefault = Neutral55,
        neutral6 = Neutral6,
        neutral7 = Neutral7,
        neutral8 = Neutral8,
        neutral85 = Neutral85,
        neutral9 = Neutral9,
        neutral95 = Neutral95,
        neutral10 = Neutral10,
        warning = Warning,
        warning2 = Warning2,
        infoFocus = InfoFocus,
        mapAlert = Secondary100,
        mapStroke = Neutral2,
        button = Primary80,
        toggle = Neutral05,
        toggleText = Neutral05,
        toggleButton = Primary120,
        mapText = Neutral8,
        switchTrack = Neutral6,
        white = Neutral05,
        statsBackground = Neutral05,
        statsBar = Secondary100,
        statsAmount = Primary120,
        statsTime = Primary100,
        networkConnectivity = Secondary120,
    )

val DarkColorPalette =
    ColorPalette(
        primary80 = Neutral5,
        primary100 = Secondary100,
        primary120 = Secondary120,
        secondary10 = Secondary10,
        secondary40 = Primary100,
        secondary100 = Primary80,
        secondary120 = Primary120,
        neutral0 = Neutral0,
        neutral05 = Neutral85,
        neutral1 = Neutral1,
        neutral15 = Neutral15,
        neutral2 = Neutral8,
        neutral3 = Neutral3,
        neutral4 = Neutral7,
        neutral5 = Neutral5,
        mapDefault = Neutral5,
        neutral6 = Neutral6,
        neutral7 = Neutral5,
        neutral8 = Neutral2,
        neutral85 = Neutral85,
        neutral9 = Neutral05,
        neutral95 = Neutral95,
        neutral10 = Neutral10,
        warning = Warning,
        warning2 = Warning2,
        infoFocus = InfoFocus,
        mapAlert = Secondary100,
        mapStroke = Neutral8,
        button = Primary100,
        toggle = Neutral85,
        toggleText = Neutral9,
        toggleButton = Neutral4,
        mapText = Neutral85,
        switchTrack = Secondary40,
        white = Neutral05,
        statsBackground = Neutral85,
        statsBar = Secondary100,
        statsAmount = Neutral5,
        statsTime = Neutral6,
        networkConnectivity = Secondary120,
    )

@Composable
fun AirAlarmUATheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val currentColorPalette = if (useDarkTheme) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(
        LocalThemeColor provides currentColorPalette,
        LocalThemeTypography provides LocalTypography.current,
        content = content,
    )
}
