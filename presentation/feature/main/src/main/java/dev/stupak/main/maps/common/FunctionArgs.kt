package dev.stupak.main.maps.common

import androidx.compose.ui.graphics.Color

data class MapArgs(
    val textColor: Color,
    val strokeColor: Color,
    val defaultColor: Color,
    val districtsSet: Map<String, Set<String>>,
    val oblastAlert: Boolean = false,
)

fun getDistrictColors(
    districts: List<String>,
    mapArgs: MapArgs,
): List<Color> =
    districts.map { district ->
        when {
            mapArgs.oblastAlert -> colorOblastAlert
            mapArgs.districtsSet["air_raid"]?.contains(district) == true -> colorOblastAlert
            district in mapArgs.districtsSet.values.flatten() -> colorAlert
            else -> mapArgs.defaultColor
        }
    }
