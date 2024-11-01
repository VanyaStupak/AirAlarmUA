
package dev.stupak.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.CompositionLocalProvider

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
    val neutral6: Color,
    val neutral7: Color,
    val neutral8: Color,
    val neutral85: Color,
    val neutral9: Color,
    val neutral95: Color,
    val neutral10: Color,
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
        neutral05 =  Neutral05,
        neutral1 =  Neutral1,
        neutral15 =  Neutral15,
        neutral2 =  Neutral2,
        neutral3 =  Neutral3,
        neutral4 =  Neutral4,
        neutral5 =  Neutral5,
        neutral6 =  Neutral6,
        neutral7 =  Neutral7,
        neutral8 =  Neutral8,
        neutral85 =  Neutral85,
        neutral9 = Neutral9,
        neutral95 =  Neutral95,
        neutral10 =  Neutral10
    )

val DarkColorPalette =
    ColorPalette(
        primary80 = Primary80,
        primary100 = Secondary100,
        primary120 = Secondary120,
        secondary10 = Secondary10,
        secondary40 = Secondary40,
        secondary100 = Primary100,
        secondary120 = Primary120,
        neutral0 = Neutral0,
        neutral05 = Neutral9,
        neutral1 = Neutral1,
        neutral15 = Neutral15,
        neutral2 = Neutral8,
        neutral3 = Neutral3,
        neutral4 = Neutral4,
        neutral5 = Neutral5,
        neutral6 = Neutral6,
        neutral7 = Neutral7,
        neutral8 = Neutral2,
        neutral85 = Neutral85,
        neutral9 = Neutral05,
        neutral95 = Neutral95,
        neutral10 = Neutral10
    )


data class AppTheme(
    val colors: ColorPalette,
    val typography: Typography
)

val LocalAppTheme = staticCompositionLocalOf<AppTheme> {
    error("No AppTheme provided")
}

@Composable
fun AirAlarmUATheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val typography = LocalTypography.current

    CompositionLocalProvider(
        LocalAppTheme provides AppTheme(colors, typography)
    ) {
        content()
    }
}