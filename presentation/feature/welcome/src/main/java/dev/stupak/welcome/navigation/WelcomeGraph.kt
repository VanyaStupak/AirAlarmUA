package dev.stupak.welcome.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object WelcomeRoute

fun NavController.navigateToWelcomeScreen(navOptions: NavOptions? = null) =
    navigate(route = WelcomeRoute, navOptions)

fun NavGraphBuilder.welcomeScreen(
    onNavigateToMain: (String) -> Unit,
    onBackClick:() -> Unit
) {
    composable<WelcomeRoute> {
        WelcomeRoute(onNavigateToMain, onBackClick)
    }
}