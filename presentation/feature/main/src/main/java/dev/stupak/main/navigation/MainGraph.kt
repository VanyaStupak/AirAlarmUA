package dev.stupak.main.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object MainRoute

fun NavController.navigateToMainScreen(navOptions: NavOptions? = null) {
    navigate(MainRoute, navOptions)
}

fun NavGraphBuilder.mainScreen(initialPage: Int, onSettingsButtonClick: () -> Unit) {
    composable<MainRoute> {
        MainRoute(initialPage, onSettingsButtonClick = onSettingsButtonClick)
    }
}