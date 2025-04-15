package dev.stupak.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object MainRoute

fun NavGraphBuilder.mainScreen(
    initialPage: Int,
    onSettingsButtonClick: () -> Unit,
) {
    composable<MainRoute> {
        MainRoute(initialPage, onSettingsButtonClick = onSettingsButtonClick)
    }
}
