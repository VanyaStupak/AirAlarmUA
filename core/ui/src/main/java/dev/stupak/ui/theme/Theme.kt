package dev.stupak.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

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

data class AppTheme(
    val colors: ColorPalette,
    val typography: Typography,
)

val LocalAppTheme =
    staticCompositionLocalOf<AppTheme> {
        error("No AppTheme provided")
    }

@Composable
fun AirAlarmUATheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val typography = LocalTypography.current

    CompositionLocalProvider(
        LocalAppTheme provides AppTheme(colors, typography),
    ) {
        content()
    }
}
