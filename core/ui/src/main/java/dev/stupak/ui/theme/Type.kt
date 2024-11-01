package dev.stupak.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.stupak.ui.R

data class Typography(

    val display1: TextStyle,
    val display2: TextStyle,
    val heading1: TextStyle,
    val heading2: TextStyle,
    val heading3: TextStyle,
    val heading4: TextStyle,
    val heading5: TextStyle,
    val heading6: TextStyle,

    val textLargeBold: TextStyle,
    val textLargeSemiBold: TextStyle,
    val textLargeMedium: TextStyle,
    val textLargeNormal: TextStyle,
    val textLargeLight: TextStyle,

    val textMediumBold: TextStyle,
    val textMediumSemiBold: TextStyle,
    val textMediumMedium: TextStyle,
    val textMediumNormal: TextStyle,
    val textMediumLight: TextStyle,

    val textRegularBold: TextStyle,
    val textRegularSemiBold: TextStyle,
    val textRegularMedium: TextStyle,
    val textRegularNormal: TextStyle,
    val textRegularLight: TextStyle,

    val textSmallBold: TextStyle,
    val textSmallSemiBold: TextStyle,
    val textSmallMedium: TextStyle,
    val textSmallNormal: TextStyle,
    val textSmallLight: TextStyle,

    val textTinyBold: TextStyle,
    val textTinySemiBold: TextStyle,
    val textTinyMedium: TextStyle,
    val textTinyNormal: TextStyle,
    val textTinyLight: TextStyle,
)


val LocalTypography = staticCompositionLocalOf {
    Typography(
        display1 = TextStyle(fontSize = 56.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 68.sp ),
        display2 = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 58.sp),
        heading1 = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 48.sp),
        heading2 = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 42.sp),
        heading3 = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 38.sp),
        heading4 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 32.sp),
        heading5 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 28.sp),
        heading6 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 24.sp),

        textLargeBold = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 27.sp),
        textLargeSemiBold = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 27.sp),
        textLargeMedium = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 27.sp),
        textLargeNormal = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 27.sp),
        textLargeLight = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Light, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 27.sp),

        textMediumBold = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 24.sp),
        textMediumSemiBold = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 24.sp),
        textMediumMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 24.sp),
        textMediumNormal = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 24.sp),
        textMediumLight = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 24.sp),

        textRegularBold = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 21.sp),
        textRegularSemiBold = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 21.sp),
        textRegularMedium = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 21.sp),
        textRegularNormal = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 21.sp),
        textRegularLight = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Light, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 21.sp),

        textSmallBold = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 18.sp),
        textSmallSemiBold = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 18.sp),
        textSmallMedium = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 18.sp),
        textSmallNormal = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 18.sp),
        textSmallLight = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 18.sp),

        textTinyBold = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 15.sp),
        textTinySemiBold = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 15.sp),
        textTinyMedium = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 15.sp),
        textTinyNormal = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 15.sp),
        textTinyLight = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Light, fontFamily = FontFamily( Font(R.font.dm_sans)), lineHeight = 15.sp),

    )
}
