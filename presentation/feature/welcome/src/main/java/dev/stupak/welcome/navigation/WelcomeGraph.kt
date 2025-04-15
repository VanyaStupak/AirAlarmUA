package dev.stupak.welcome.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object WelcomeRoute

fun NavGraphBuilder.welcomeScreen(
    onNavigateToMain: (String) -> Unit,
    onBackClick: () -> Unit,
) {
    composable<WelcomeRoute> {
        WelcomeRoute(onNavigateToMain, onBackClick)
    }
}
