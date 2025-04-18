package com.example.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

fun NavGraphBuilder.settingsScreen(
    onBackClick: () -> Unit,
    onChangeRegionClick: () -> Unit,
    onThemeUpdated: (Boolean) -> Unit,
) {
    composable<SettingsRoute> {
        SettingsRoute(
            onBackClick = onBackClick,
            onChangeRegionClick = onChangeRegionClick,
            onThemeUpdated = onThemeUpdated,
        )
    }
}
