package dev.stupak.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.settings.navigation.SettingsRoute
import com.example.settings.navigation.settingsScreen
import dev.stupak.main.navigation.MainRoute
import dev.stupak.main.navigation.mainScreen
import dev.stupak.navigation.ext.safeNavigation
import dev.stupak.welcome.navigation.WelcomeRoute
import dev.stupak.welcome.navigation.welcomeScreen

@Composable
fun NavHost(
    onThemeUpdated: (Boolean) -> Unit,
    initialPage: Int,
) {
    val navController = rememberNavController()

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    val isFirstRun = sharedPreferences.getBoolean("is_first_run", true)
    val startDestination = if (isFirstRun) WelcomeRoute else MainRoute

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        mainScreen(initialPage) {
            navController.safeNavigation(route = SettingsRoute)
        }
        welcomeScreen(
            onBackClick = { navController.popBackStack() },
            onNavigateToMain = { navController.safeNavigation(route = MainRoute) },
        )
        settingsScreen(
            onBackClick = { navController.popBackStack() },
            onChangeRegionClick = { navController.safeNavigation(route = WelcomeRoute) },
            onThemeUpdated = onThemeUpdated,
        )
    }
}
